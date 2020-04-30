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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobiliaria.api.entities.Administrador;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.AdministradorServiceImpl;
import br.com.imobiliaria.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {
	
	private static final Logger log = LoggerFactory.getLogger(AdministradorController.class);

	
	@Autowired
	private AdministradorServiceImpl administradorServiceImpl;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	/**
	 * Retorna a listagem de lançamentos de um funcionário.
	 * 
	 * @param funcionarioId
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping
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
	 * Atualiza os dados de um funcionário.
	 * 
	 * @param id
	 * @param funcionarioDto
	 * @param result
	 * @return ResponseEntity<Response<FuncionarioDto>>
	 * @throws NoSuchAlgorithmException
	 */
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Response<Administrador>> atualizar(@PathVariable("id") Long id,
//			@Valid @RequestBody Administrador administrador, BindingResult result) throws NoSuchAlgorithmException {
//		log.info("Atualizando administrador: {}", administrador.toString());
//		Response<Administrador> response = new Response<Administrador>();
//
//		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(id);
//		if (!funcionario.isPresent()) {
//			result.addError(new ObjectError("funcionario", "Funcionário não encontrado."));
//		}
//
//		this.atualizarDadosFuncionario(funcionario.get(), funcionarioDto, result);
//
//		if (result.hasErrors()) {
//			log.error("Erro validando funcionário: {}", result.getAllErrors());
//			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
//			return ResponseEntity.badRequest().body(response);
//		}
//
//		this.administradorServiceImpl.persistir(funcionario.get());
//
//		return ResponseEntity.ok(response);
//	}

	/**
	 * Atualiza os dados do funcionário com base nos dados encontrados no DTO.
	 * 
	 * @param funcionario
	 * @param funcionarioDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private void atualizarDadosFuncionario(Administrador administrador, BindingResult result)
			throws NoSuchAlgorithmException {

			this.administradorServiceImpl.buscarPorEmail(administrador.getEmail())
					.ifPresent(func -> result.addError(new ObjectError("email", "Email já existente.")));

		if (!administrador.getSenha().isEmpty()) {
			administrador.setSenha(PasswordUtils.gerarBCrypt(administrador.getSenha()));
		}
	}
	
	
}
