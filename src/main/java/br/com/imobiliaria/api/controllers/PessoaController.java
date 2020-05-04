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
import br.com.imobiliaria.api.entities.Cliente;
import br.com.imobiliaria.api.entities.Pessoa;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.AdministradorServiceImpl;
import br.com.imobiliaria.api.services.impl.ClienteServiceImpl;
import br.com.imobiliaria.api.services.impl.PessoaServiceImpl;
import br.com.imobiliaria.api.utils.PasswordUtils;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
@Api(value = "Pessoa", description = "API REST para pessoa", tags = { "Pessoa" })
public class PessoaController {

	private static final Logger log = LoggerFactory.getLogger(PessoaController.class);

	@Autowired
	private PessoaServiceImpl pessoaServiceImpl;
	
	@Autowired
	private AdministradorServiceImpl administradorServiceImpl;
	
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
	@GetMapping(value = "administradores/codigo/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Administrador>> buscarAdministradorPorCodigo(@PathVariable("codigo") Long codigo){
	
		log.info("Buscando administrador por codigo");
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
	 * Retorna um cliente a partir do codigo
	 * 
	 * @param codigo
	 * @return ResponseEntity<Response<Administrador>>
	 */
	@GetMapping(value = "clientes/codigo/{codigo}")
	public ResponseEntity<Response<Cliente>> buscarClientePorCodigo(@PathVariable("codigo") Long codigo){
	
		log.info("Buscando administrador por codigo");
		Response<Cliente> response = new Response<Cliente>();

		Optional<Cliente> cliente = this.clienteServiceImpl.buscarPorCodigo(codigo);

		if (!cliente.isPresent()) {
			log.info("Erro ao buscar devido ao administrador ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao buscar administrador. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
	
		response.setData(cliente.get());
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna a listagem de administradores
	 * 
	 * @param pag, ord, dir.
	 * @return ResponseEntity<Response<Page<Administrador>>>
	 */
	@GetMapping("administradores/")
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
	 * Retorna a listagem de clientes
	 * 
	 * @param pag, ord, dir.
	 * @return ResponseEntity<Response<Page<Cliente>>>
	 */
	@GetMapping("clientes/")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Cliente>>> listarTodosClientes(
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
	 * Cadastra um administrador no sistema.
	 * 
	 * @param administrador
	 * @param result
	 * @return ResponseEntity<Response<Administrador>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("/administradores")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Administrador>> cadastrarAdministrador(@Valid @RequestBody Administrador administrador,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Administrador: {}", administrador.toString());
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
	 * Cadastra um cliente no sistema.
	 * 
	 * @param cliente
	 * @param result
	 * @return ResponseEntity<Response<Cliente>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("/clientes")
	public ResponseEntity<Response<Cliente>> cadastrarCliente(@Valid @RequestBody Cliente cliente,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Cliente: {}", cliente.toString());
		Response<Cliente> response = new Response<Cliente>();

		validarDadosExistentes(cliente, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		cliente.setSenha(PasswordUtils.gerarBCrypt(cliente.getSenha()));
		
		this.clienteServiceImpl.persistir(cliente);	
		
		response.setData(cliente);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se a pessoa já está cadastrada na base de dados.
	 * 
	 * @param pessoa
	 * @param result
	 */
	private void validarDadosExistentes(Pessoa pessoa, BindingResult result) {
		Optional<Pessoa> pessoaValidada = this.pessoaServiceImpl.buscarPorEmail(pessoa.getEmail());

		if (pessoaValidada.isPresent()) {
			result.addError(new ObjectError("pessoa", "Pessoa já cadastrado cadastrado."));
		}

		this.pessoaServiceImpl.buscarPorEmail(pessoa.getEmail())
			.ifPresent(cli -> result.addError(new ObjectError("pessoa", "email de pessoa já existente")));
	}

	/**
	 * Atualiza os dados de um cliente.
	 * 
	 * @param id
	 * @param cliente
	 * @param result
	 * @return ResponseEntity<Response<Cliente>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "clientes/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Cliente>> atualizarAdministrador(@PathVariable("codigo") Long codigo,
			@Valid @RequestBody Cliente cliente, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando cliente: {}", cliente.toString());
		Response<Cliente> response = new Response<Cliente>();

		Optional<Cliente> cli = this.clienteServiceImpl.buscarPorCodigo(codigo);
		if (!cli.isPresent()) {
			result.addError(new ObjectError("cliente", "cliente não encontrado."));
		}
		
		cliente.setPerfil(cli.get().getPerfil());
		cliente.setCodigo(cli.get().getCodigo());
		cliente.setDataCriacao(cli.get().getDataCriacao());
		
		if(!cli.get().getEmail().isEmpty()) cliente.setEmail(cli.get().getEmail().toString());
		
		if (!cliente.getSenha().isEmpty()) {
			cliente.setSenha(PasswordUtils.gerarBCrypt(cliente.getSenha()));
		}

		if (result.hasErrors()) {
			log.error("Erro validando administrador: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(cliente);
		this.clienteServiceImpl.persistir(cliente);

		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza os dados de um administrador.
	 * 
	 * @param id
	 * @param Administrador
	 * @param result
	 * @return ResponseEntity<Response<Administrador>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "administradores/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Administrador>> atualizarCliente(@PathVariable("codigo") Long codigo,
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
	@DeleteMapping(value = "/administradores/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> removerAdministrador(@PathVariable("codigo") Long codigo) {
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
	
	/**
	 * Remove um cliente por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/clientes/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> removerCliente(@PathVariable("codigo") Long codigo) {
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

