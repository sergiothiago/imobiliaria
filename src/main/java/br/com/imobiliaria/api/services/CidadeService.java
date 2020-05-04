package br.com.imobiliaria.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.imobiliaria.api.entities.Cidade;

public interface CidadeService {
	
	/**
	 * Retorna um administador dado um codigo.
	 * 
	 * @param codigo
	 * @return Optional<Cidade>
	 */
	Optional<Cidade> buscarPorCodigo(Long codigo);
	
	/**
	 * Retorna uma lista paginada de cidades
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Cidade>
	 */
	Page<Cidade> buscarTodos(PageRequest pageRequest);

	
	/**
	 * Retorna uma lista paginada de cidades
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Cidade>
	 */
	Page<Cidade> buscarPorNome(String nome, PageRequest pageRequest);
	
	/**
	 * Cadastra uma nova cidade na base de dados.
	 * 
	 * @param cidade
	 * @return cidade
	 */
	Cidade persistir(Cidade cidade);
}
