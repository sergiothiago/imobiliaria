package br.com.imobiliaria.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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

import br.com.imobiliaria.api.entities.Apartamento;
import br.com.imobiliaria.api.entities.Casa;
import br.com.imobiliaria.api.entities.Galpao;
import br.com.imobiliaria.api.entities.Imovel;
import br.com.imobiliaria.api.entities.Terreno;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.ImovelServiceImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/imoveis")
@CrossOrigin(origins = "*")
@Api(value = "Imovel", description = "API REST para imoveis", tags = { "Imovel" })
public class ImovelController {
	
	private static final Logger log = LoggerFactory.getLogger(ImovelController.class);
	
	@Autowired
	private ImovelServiceImpl imovelServiceImpl;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	@PersistenceContext
	protected EntityManager manager;

	/**
	 * Retorna a listagem de lançamentos de um funcionário.
	 * 
	 * @param funcionarioId
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping()
	public ResponseEntity<Response<Page<Imovel>>> listarTodos(
		@RequestParam(value = "pag", defaultValue = "0") int pag,
		@RequestParam(value = "ord", defaultValue = "codigo") String ord,
		@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
	
		log.info("Buscando clientes , página: {}", pag);
		Response<Page<Imovel>> response = new Response<Page<Imovel>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		// Buscar todos
		Page<Imovel> imoveis = this.imovelServiceImpl.buscarTodos(pageRequest);

		response.setData(imoveis);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna a listagem de imoveis filtrando por um parameto
	 * 
	 * @param funcionarioId
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping("/buscasimples")
	public ResponseEntity<Response<Page<Imovel>>> buscaSimples(
		@RequestParam(value = "param", defaultValue = "") String param,
		@RequestParam(value = "pag", defaultValue = "0") int pag,
		@RequestParam(value = "ord", defaultValue = "codigo") String ord,
		@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
	
		log.info("Buscando imoveis pela busca simples , página: {}", pag);
		Response<Page<Imovel>> response = new Response<Page<Imovel>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		Page<Imovel> imoveis = this.imovelServiceImpl.buscaSimples(param, pageRequest);
		response.setData(imoveis);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna a listagem de imoveis filtrando por um parameto
	 * 
	 * @param 
	 * @return ResponseEntity<Response<Imovel>>
	 */
	@Transactional
	@GetMapping("/buscaavancada")
	public ResponseEntity<Response<List<Imovel>>> buscaAvancada(
		@RequestParam(value = "pag", defaultValue = "0") int pag,
		@RequestParam(value = "ord", defaultValue = "codigo") String ord,
		@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
	
		log.info("Buscando imoveis pela busca avancada , página: {}", pag);
		Response<List<Imovel>> response = new Response<List<Imovel>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		StringBuilder consultaAvancadaBuilder = new StringBuilder();
		consultaAvancadaBuilder.append("SELECT i FROM Imovel i ");
		
	    String consultaAvancadaStr = consultaAvancadaBuilder.toString();
		
		Query query = manager.createQuery(consultaAvancadaStr, Imovel.class);
		query.setFirstResult((pag) * qtdPorPagina); 
		query.setMaxResults(pag);
		
		List<Imovel> imoveis = (List<Imovel>) query.getResultList();
		response.setData(imoveis);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Cadastra uma casa no sistema.
	 * 
	 * @param casa
	 * @param result
	 * @return ResponseEntity<Response<Casa>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("/casa")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Casa>> cadastrarCasa(@Valid @RequestBody Casa casa,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando casa: {}", casa.toString());
		Response<Casa> response = new Response<Casa>();

		validarDadosImovelExistentes(casa, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.imovelServiceImpl.persistir(casa);	
		
		response.setData(casa);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Cadastra um apartamento no sistema.
	 * 
	 * @param apartamento
	 * @param result
	 * @return ResponseEntity<Response<Casa>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("/apartamento")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Apartamento>> cadastrarApartamento(@Valid @RequestBody Apartamento apartamento,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando apartamento: {}", apartamento.toString());
		Response<Apartamento> response = new Response<Apartamento>();

		validarDadosImovelExistentes(apartamento, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de terreno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.imovelServiceImpl.persistir(apartamento);	
		
		response.setData(apartamento);
		return ResponseEntity.ok(response);
	}
	
	
	/**
	 * Cadastra um terreno no sistema.
	 * 
	 * @param apartamento
	 * @param result
	 * @return ResponseEntity<Response<Casa>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("/terreno")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Terreno>> cadastrarTerreno(@Valid @RequestBody Terreno terreno,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando terreno: {}", terreno.toString());
		Response<Terreno> response = new Response<Terreno>();

		validarDadosImovelExistentes(terreno, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de terreno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.imovelServiceImpl.persistir(terreno);	
		
		response.setData(terreno);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Cadastra um galpao no sistema.
	 * 
	 * @param apartamento
	 * @param result
	 * @return ResponseEntity<Response<Casa>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("/galpao")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Galpao>> cadastrarGalpao(@Valid @RequestBody Galpao galpao,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando terreno: {}", galpao.toString());
		Response<Galpao> response = new Response<Galpao>();

		validarDadosImovelExistentes(galpao, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de galpao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.imovelServiceImpl.persistir(galpao);	
		
		response.setData(galpao);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se o administrador já está cadastrado na base de dados.
	 * 
	 * @param administrador
	 * @param result
	 */
	private void validarDadosImovelExistentes(Imovel imovel, BindingResult result) {

		this.imovelServiceImpl.buscarPorReferencia(imovel.getReferencia())
			.ifPresent(imv -> result.addError(new ObjectError("imovel", "referencia de imovel já existente")));
	}
	
	/**
	 * Remove um imovel por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<String>>
	 */
	@DeleteMapping(value = "/imovel/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> removerCliente(@PathVariable("codigo") Long codigo) {
		log.info("Removendo imovel: {}", codigo);
		Response<String> response = new Response<String>();
		Optional<Imovel> imovel = this.imovelServiceImpl.buscarPorCodigo(codigo);

		if (!imovel.isPresent()) {
			log.info("Erro ao remover imovel devido ID: {} ser inválido.", codigo);
			response.getErrors().add("Erro ao remover imovel. Registro não encontrado para o id " + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData("Imovel removido com Sucesso!");
		this.imovelServiceImpl.remover(codigo);
		return ResponseEntity.ok(response);
	}

	
	
}
