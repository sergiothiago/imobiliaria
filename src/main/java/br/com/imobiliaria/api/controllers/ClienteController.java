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

import br.com.imobiliaria.api.dtos.ClienteDTO;
import br.com.imobiliaria.api.entities.Cliente;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.ClienteService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	/**
	 * Retorna a listagem de lançamentos de um funcionário.
	 * 
	 * @param funcionarioId
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping()
	public ResponseEntity<Response<Page<ClienteDTO>>> listarTodos(
		@RequestParam(value = "pag", defaultValue = "0") int pag,
		@RequestParam(value = "ord", defaultValue = "id") String ord,
		@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
	
		log.info("Buscando clientes , página: {}", pag);
		Response<Page<ClienteDTO>> response = new Response<Page<ClienteDTO>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		Page<Cliente> clientes = this.clienteService.buscarTodos(pageRequest);

		Page<ClienteDTO> clientesDto = clientes.map(cli -> this.converterClienteParaDTO(cli));
		
		response.setData(clientesDto);
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
	public ResponseEntity<Response<ClienteDTO>> cadastrar(@Valid @RequestBody ClienteDTO cadastroClienteDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Cliente: {}", cadastroClienteDTO.toString());
		Response<ClienteDTO> response = new Response<ClienteDTO>();

		validarDadosExistentes(cadastroClienteDTO, result);
		
		Cliente cliente = this.converterDtoParaCliente(cadastroClienteDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.persistir(cliente);	

		response.setData(this.converterClienteParaDTO(cliente, result));
		return ResponseEntity.ok(response);
	}
	
	private Cliente converterDtoParaCliente(ClienteDTO cadastroClienteDTO,BindingResult result )
			throws NoSuchAlgorithmException {
		
		Cliente cliente = new Cliente();
		cliente.setNome(cadastroClienteDTO.getNome());
		cliente.setEmail(cadastroClienteDTO.getEmail());
		cliente.setSenha(cadastroClienteDTO.getSenha());
		
		return cliente;
	}
	
	private ClienteDTO converterClienteParaDTO(Cliente cliente ,BindingResult result )
			throws NoSuchAlgorithmException {
		
		ClienteDTO cadastroClienteDTO = new ClienteDTO();
		cadastroClienteDTO.setEmail(cliente.getEmail());
		cadastroClienteDTO.setId(cliente.getCodigo());
		cadastroClienteDTO.setNome(cliente.getNome());
		
		return cadastroClienteDTO;
	}
	
	private ClienteDTO converterClienteParaDTO(Cliente cliente) {
		ClienteDTO cadastroClienteDTO = new ClienteDTO();
		cadastroClienteDTO.setEmail(cliente.getEmail());
		cadastroClienteDTO.setId(cliente.getCodigo());
		cadastroClienteDTO.setNome(cliente.getNome());
		
		return cadastroClienteDTO;
	}
	
	
	/**
	 * Verifica se o cliente já está cadastrado na base de dados.
	 * 
	 * @param cadastroPFDto
	 * @param result
	 */
	private void validarDadosExistentes(ClienteDTO cadastroClienteDTO, BindingResult result) {
		Optional<Cliente> cliente = this.clienteService.buscarPorEmail(cadastroClienteDTO.getEmail());
		
		if (!cliente.isPresent()) {
			result.addError(new ObjectError("cliente", "Cliente não cadastrado."));
		}

		this.clienteService.buscarPorEmail(cadastroClienteDTO.getEmail())
			.ifPresent(cli -> result.addError(new ObjectError("cliente", "email de cliente já existente")));
	}
	
	
	
}
