package br.com.imobiliaria.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.imobiliaria.api.entities.Administrador;

public interface AdministradorService {

	/**
	 * Retorna um cliente dado um Email.
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	Optional<Administrador> buscarPorEmail(String email);
	
	
	/**
	 * Retorna uma lista paginada de clientes
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Cliente>
	 */
	Page<Administrador> buscarPorNome(String nome, PageRequest pageRequest);
	
	/**
	 * Retorna uma lista paginada de clientes
	 * 
	 * @param nome
	 * @param pageRequest
	 * @return Page<Cliente>
	 */
	Page<Administrador> buscarTodos(PageRequest pageRequest);
	
	/**
	 * Cadastra um novo cliente na base de dados.
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	Administrador persistir(Administrador administrador);
	
}
