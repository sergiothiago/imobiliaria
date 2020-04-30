package br.com.imobiliaria.api.services;

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
	 * @return Page<Lancamento>
	 */
	Page<Imovel> buscarTodos(PageRequest pageRequest);
	
}
