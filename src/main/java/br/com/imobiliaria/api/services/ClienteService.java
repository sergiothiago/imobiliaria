package br.com.imobiliaria.api.services;
 
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.imobiliaria.api.entities.Cliente;

 public interface ClienteService {
 
	/**
	 * Retorna um cliente dado um codigo.
	 * 
	 * @param codigo
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorCodigo(Long codigo);
	
	/**
	 * Retorna um cliente dado um Email.
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorEmail(String email);
	
	
	/**
	 * Retorna uma lista paginada de clientes
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Cliente>
	 */
	Page<Cliente> buscarPorNome(String nome, PageRequest pageRequest);
	
	/**
	 * Retorna uma lista paginada de clientes
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Cliente>
	 */
	Page<Cliente> buscarTodos(PageRequest pageRequest);
	
	/**
	 * Cadastra um novo cliente na base de dados.
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	Cliente persistir(Cliente cliente);
	
	/**
	 * Remove um cliente da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);
	
}
