package br.com.imobiliaria.api.services;

import java.util.List;
import java.util.Optional;

import br.com.imobiliaria.api.entities.Cidade;

public interface CidadeService {
	
	/**
	 * Retorna uma cidade dado um codigo.
	 * 
	 * @param codigo
	 * @return Optional<Cidade>
	 */
	Optional<Cidade> buscarPorCodigo(Long codigo);
	
	/**
	 * Retorna uma lista nao paginada de cidades
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Cidade>
	 */
	List<Cidade> buscarTodos();
	
	/**
	 * Cadastra uma nova cidade na base de dados.
	 * 
	 * @param cidade
	 * @return cidade
	 */
	Cidade persistir(Cidade cidade);
	
	/**
	 * Remove uma cidade da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);
}
