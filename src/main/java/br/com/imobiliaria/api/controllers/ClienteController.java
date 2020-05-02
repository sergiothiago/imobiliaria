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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobiliaria.api.entities.Cliente;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.ClienteServiceImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@Api(value = "Cliente", description = "API REST para clientes", tags = { "Cliente" })
public class ClienteController {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	/**
	 * Retorna um administrador a partir do codigo
	 * 
	 * @param codigo
	 * @return ResponseEntity<Response<Administrador>>
	 */
	@GetMapping(value = "codigo/{codigo}")
	public ResponseEntity<Response<Cliente>> buscarPorCodigo(@PathVariable("codigo") Long codigo){
	
		log.info("Buscando cliente por codigo");
		Response<Cliente> response = new Response<Cliente>();

		Optional<Cliente> cliente = this.clienteServiceImpl.buscarPorCodigo(codigo);

		if (!cliente.isPresent()) {
			log.info("Erro ao buscar devido ao cliente ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao buscar cliente. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
	
		response.setData(cliente.get());
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna a listagem de lançamentos de um funcionário.
	 * 
	 * @param funcionarioId
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Cliente>>> listarTodos(
		@RequestParam(value = "pag", defaultValue = "0") int pag,
		@RequestParam(value = "ord", defaultValue = "codigo") String ord,
		@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
	
		log.info("Buscando clientes , página: {}", pag);
		Response<Page<Cliente>> response = new Response<Page<Cliente>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		Page<Cliente> clientes = this.clienteServiceImpl.buscarTodos(pageRequest);
		
		response.setData(clientes);
		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra um cliente no sistema.
	 * 
	 * @param cadastroClienteDTO
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Cliente>> cadastrar(@Valid @RequestBody Cliente cliente,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Cliente: {}", cliente.toString());
		Response<Cliente> response = new Response<Cliente>();

		validarDadosExistentes(cliente, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.clienteServiceImpl.persistir(cliente);	
		
		response.setData(cliente);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se o cliente já está cadastrado na base de dados.
	 * 
	 * @param cadastroPFDto
	 * @param result
	 */
	private void validarDadosExistentes(Cliente cliente, BindingResult result) {
		Optional<Cliente> clienteValidado = this.clienteServiceImpl.buscarPorEmail(cliente.getEmail());

		if (clienteValidado.isPresent()) {
			result.addError(new ObjectError("cliente", "Cliente já cadastrado cadastrado."));
		}

		this.clienteServiceImpl.buscarPorEmail(cliente.getEmail())
			.ifPresent(cli -> result.addError(new ObjectError("cliente", "email de cliente já existente")));
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
		log.info("Removendo cliente: {}", codigo);
		Response<String> response = new Response<String>();
		Optional<Cliente> cliente = this.clienteServiceImpl.buscarPorCodigo(codigo);

		if (!cliente.isPresent()) {
			log.info("Erro ao remover devido ao cliente ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao remover cliente. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData("Cliente removido com Sucesso!");
		this.clienteServiceImpl.remover(codigo);
		return ResponseEntity.ok(response);
	}

}
