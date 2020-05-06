package br.com.imobiliaria.api.services;

import java.util.List;
import java.util.Optional;

import br.com.imobiliaria.api.entities.Bairro;

public interface BairroService {

	/**
	 * Retorna um bairro dado um codigo.
	 * 
	 * @param codigo
	 * @return Optional<Bairro>
	 */
	Optional<Bairro> buscarPorCodigo(Long codigo);
	
	/**
	 * Retorna uma lista paginada de bairros
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return List<Bairro>
	 */
	List<Bairro> buscarTodos();
	
	/**
	 * Cadastra um novo bairros na base de dados.
	 * 
	 * @param bairros
	 * @return bairros
	 */
	Bairro persistir(Bairro bairro);
	
	/**
	 * Remove um bairro da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);
}
