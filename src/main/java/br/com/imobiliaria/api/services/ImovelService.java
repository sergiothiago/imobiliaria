package br.com.imobiliaria.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.imobiliaria.api.entities.Imovel;

public interface ImovelService {
	
	/**
	 * Persiste um imovel na base de dados.
	 * 
	 * @param imovel
	 * @return imovel
	 */
	Imovel persistir(Imovel imovel);
	
	/**
	 * Retorna uma lista paginada de imoveis
	 * 
	 * @param pageRequest
	 * @return Page<Imovel>
	 */
	Page<Imovel> buscarTodos(PageRequest pageRequest);
	
	/**
	 * Retorna uma lista paginada de imoveis de acordo com um parametro
	 * 
	 * @param pageRequest
	 * @return Page<Imovel>
	 */
	Page<Imovel> buscaSimples(String param, PageRequest pageRequest);
	
	/**
	 * Retorna um imovel dada uma referencia.
	 * 
	 * @param codigo
	 * @return Optional<Imovel>
	 */
	Optional<Imovel> buscarPorReferencia(String referencia);
	
	/**
	 * Retorna um imovel dado um codigo.
	 * 
	 * @param codigo
	 * @return Optional<Imovel>
	 */
	Optional<Imovel> buscarPorCodigo(Long codigo);
	
	/**
	 * Remove um imovel da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);

}
