package br.com.imobiliaria.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.imobiliaria.api.entities.Cliente;
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
	
	/**
	 * Retorna um imovel dada uma referencia.
	 * 
	 * @param codigo
	 * @return Optional<Imovel>
	 */
	Optional<Imovel> buscarPorReferencia(String referencia);

}
