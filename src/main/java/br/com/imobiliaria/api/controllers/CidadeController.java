package br.com.imobiliaria.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.imobiliaria.api.entities.Cidade;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.CidadeServiceImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/cidades")
@CrossOrigin(origins = "*")
@Api(value = "Cidade", description = "API REST para cidades", tags = { "Cidade" })
public class CidadeController {

	private static final Logger log = LoggerFactory.getLogger(CidadeController.class);

	@Autowired
	private CidadeServiceImpl cidadeServiceImpl;

	/**
	 * Retorna a listagem de cidades sem paginacao
	 * 
	 * @param funcionarioId
	 * @return ResponseEntity<Response<Cidade>>
	 */
	@GetMapping()
	public ResponseEntity<Response<List<Cidade>>> listarTodos() {
	
		log.info("Buscando cidades");
		Response<List<Cidade>> response = new Response<List<Cidade>>();

		// Buscar todos
		List<Cidade> cidades = this.cidadeServiceImpl.buscarTodos();

		response.setData(cidades);
		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra uma cidade no sistema.
	 * 
	 * @param casa
	 * @param result
	 * @return ResponseEntity<Response<Casa>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Cidade>> cadastrarCidade(@Valid @RequestBody Cidade cidade,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando cidade: {}", cidade.toString());
		Response<Cidade> response = new Response<Cidade>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro cidade: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.cidadeServiceImpl.persistir(cidade);	
		
		response.setData(cidade);
		return ResponseEntity.ok(response);
	}
	

	/**
	 * Remove uma cidade por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> removerCidade(@PathVariable("codigo") Long codigo) {
		log.info("Removendo cidade: {}", codigo);
		Response<String> response = new Response<String>();
		Optional<Cidade> cidade = this.cidadeServiceImpl.buscarPorCodigo(codigo);

		if (!cidade.isPresent()) {
			log.info("Erro ao remover cidade devido ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao remover cidade. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData("Cidade removida com Sucesso!");
		this.cidadeServiceImpl.remover(codigo);
		return ResponseEntity.ok(response);
	}



	/**
	 * Atualiza os dados de uma cidade.
	 * 
	 * @param id
	 * @param codade
	 * @param result
	 * @return ResponseEntity<Response<Cidade>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Cidade>> atualizarCidade(@PathVariable("codigo") Long codigo,
			@Valid @RequestBody Cidade cidade, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando cidade: {}", cidade.toString());
		Response<Cidade> response = new Response<Cidade>();

		Optional<Cidade> cid = this.cidadeServiceImpl.buscarPorCodigo(codigo);
		if (!cid.isPresent()) {
			result.addError(new ObjectError("cliente", "cliente não encontrado."));
		}
		
		cidade.setBairros(cid.get().getBairros());
		cidade.setCodigo(cid.get().getCodigo());
		cidade.setDataCriacao(cid.get().getDataCriacao());
		
		if (result.hasErrors()) {
			log.error("Erro validando cidade: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(cidade);
		this.cidadeServiceImpl.persistir(cidade);

		return ResponseEntity.ok(response);
	}
	
}
