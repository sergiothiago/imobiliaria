package br.com.imobiliaria.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.imobiliaria.api.entities.Pessoa;

public interface PessoaService {

	/**
	 * Retorna um administador dado um Email.
	 * 
	 * @param email
	 * @return Optional<Administrador>
	 */
	Optional<Pessoa> buscarPorEmail(String email);
	
	/**
	 * Retorna um administador dado um codigo.
	 * 
	 * @param codigo
	 * @return Optional<Administrador>
	 */
	Optional<Pessoa> buscarPorCodigo(Long codigo);
	
	/**
	 * Retorna uma lista paginada de administradores
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Administrador>
	 */
	Page<Pessoa> buscarPorNome(String nome, PageRequest pageRequest);
	
	/**
	 * Retorna uma lista paginada de clientes
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Administrador>
	 */
	Page<Pessoa> buscarTodos(PageRequest pageRequest);
	
	/**
	 * Cadastra um novo cliente na base de dados.
	 * 
	 * @param administrador
	 * @return Administrador
	 */
	Pessoa persistir(Pessoa administrador);

	/**
	 * Remove um administrador da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);
}
