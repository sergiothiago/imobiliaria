package br.com.imobiliaria.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobiliaria.api.entities.Imovel;
import br.com.imobiliaria.api.response.Response;
import br.com.imobiliaria.api.services.impl.ImovelServiceImpl;

@RestController
@RequestMapping("/api/imoveis")
@CrossOrigin(origins = "*")
public class ImovelController {
	
	private static final Logger log = LoggerFactory.getLogger(ImovelController.class);
	
	@Autowired
	private ImovelServiceImpl imovelServiceImpl;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

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
		
		Page<Imovel> imoveis = this.imovelServiceImpl.buscarTodos(pageRequest);

		response.setData(imoveis);
		return ResponseEntity.ok(response);
	}
	
	

}
