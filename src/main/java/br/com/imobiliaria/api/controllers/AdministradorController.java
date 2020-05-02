package br.com.imobiliaria.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobiliaria.api.entities.Administrador;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.AdministradorServiceImpl;
import br.com.imobiliaria.api.utils.PasswordUtils;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
@Api(value = "Administrador", description = "API REST para administradores", tags = { "Administrador" })
public class AdministradorController {
	
	private static final Logger log = LoggerFactory.getLogger(AdministradorController.class);

	
	@Autowired
	private AdministradorServiceImpl administradorServiceImpl;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	/**
	 * Retorna um administrador a partir do codigo
	 * 
	 * @param codigo
	 * @return ResponseEntity<Response<Administrador>>
	 */
	@GetMapping(value = "codigo/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Administrador>> buscarPorCodigo(@PathVariable("codigo") Long codigo){
	
		log.info("Buscando cliente por codigo");
		Response<Administrador> response = new Response<Administrador>();

		Optional<Administrador> administrador = this.administradorServiceImpl.buscarPorCodigo(codigo);

		if (!administrador.isPresent()) {
			log.info("Erro ao buscar devido ao administrador ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao buscar administrador. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
	
		response.setData(administrador.get());
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna a listagem de administradores
	 * 
	 * @param pag, ord, dir.
	 * @return ResponseEntity<Response<Page<Administrador>>>
	 */
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Administrador>>> listarTodos(
		@RequestParam(value = "pag", defaultValue = "0") int pag,
		@RequestParam(value = "ord", defaultValue = "codigo") String ord,
		@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
	
		log.info("Buscando clientes , página: {}", pag);
		Response<Page<Administrador>> response = new Response<Page<Administrador>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		Page<Administrador> administradores = this.administradorServiceImpl.buscarTodos(pageRequest);
		
		response.setData(administradores);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Cadastra um administrador no sistema.
	 * 
	 * @param administrador
	 * @param result
	 * @return ResponseEntity<Response<Administrador>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Administrador>> cadastrar(@Valid @RequestBody Administrador administrador,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Cliente: {}", administrador.toString());
		Response<Administrador> response = new Response<Administrador>();

		validarDadosExistentes(administrador, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		administrador.setSenha(PasswordUtils.gerarBCrypt(administrador.getSenha()));
		
		this.administradorServiceImpl.persistir(administrador);	
		
		response.setData(administrador);
		return ResponseEntity.ok(response);
	}

	/**
	 * Verifica se o administrador já está cadastrado na base de dados.
	 * 
	 * @param administrador
	 * @param result
	 */
	private void validarDadosExistentes(Administrador administrador, BindingResult result) {
		Optional<Administrador> administradorValidado = this.administradorServiceImpl.buscarPorEmail(administrador.getEmail());

		if (administradorValidado.isPresent()) {
			result.addError(new ObjectError("administrador", "Administrador já cadastrado cadastrado."));
		}

		this.administradorServiceImpl.buscarPorEmail(administrador.getEmail())
			.ifPresent(cli -> result.addError(new ObjectError("administrador", "email de administrador já existente")));
	}

	/**
	 * Atualiza os dados de um administrador.
	 * 
	 * @param id
	 * @param funcionarioDto
	 * @param result
	 * @return ResponseEntity<Response<FuncionarioDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Administrador>> atualizar(@PathVariable("codigo") Long codigo,
			@Valid @RequestBody Administrador administrador, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando administrador: {}", administrador.toString());
		Response<Administrador> response = new Response<Administrador>();

		Optional<Administrador> admin = this.administradorServiceImpl.buscarPorCodigo(codigo);
		if (!admin.isPresent()) {
			result.addError(new ObjectError("administrador", "Administrador não encontrado."));
		}
		
		administrador.setPerfil(admin.get().getPerfil());
		administrador.setCodigo(admin.get().getCodigo());
		administrador.setDataCriacao(admin.get().getDataCriacao());
		
		if(!admin.get().getEmail().isEmpty()) administrador.setEmail(admin.get().getEmail().toString());
		
		if (!administrador.getSenha().isEmpty()) {
			administrador.setSenha(PasswordUtils.gerarBCrypt(administrador.getSenha()));
		}

		if (result.hasErrors()) {
			log.error("Erro validando administrador: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(administrador);
		this.administradorServiceImpl.persistir(administrador);

		return ResponseEntity.ok(response);
	}
	
	/**
	 * Remove um administrador por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> remover(@PathVariable("codigo") Long codigo) {
		log.info("Removendo administrador: {}", codigo);
		Response<String> response = new Response<String>();
		Optional<Administrador> administrador = this.administradorServiceImpl.buscarPorCodigo(codigo);

		if (!administrador.isPresent()) {
			log.info("Erro ao remover devido ao administrador ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao remover administrador. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData("Administrador removido com Sucesso!");
		this.administradorServiceImpl.remover(codigo);
		return ResponseEntity.ok(response);
	}


}
