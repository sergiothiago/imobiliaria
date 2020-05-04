package br.com.imobiliaria.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.imobiliaria.api.entities.Administrador;

public interface AdministradorService {

	/**
-	 * Retorna um administador dado um Email.
-	 * 
-	 * @param email
-	 * @return Optional<Administrador>
-	 */
	Optional<Administrador> buscarPorEmail(String email);
	
	/**
-	 * Retorna um administador dado um codigo.
-	 * 
-	 * @param codigo
-	 * @return Optional<Administrador>
-	 */
	Optional<Administrador> buscarPorCodigo(Long codigo);

	/**
-	 * Retorna uma lista paginada de administradores
-	 * 
-	 * @param nome
-	 * @param pageRequest
-	 * @return Page<Administrador>
-	 */
	Page<Administrador> buscarPorNome(String nome, PageRequest pageRequest);
	
	/**
-	 * Retorna uma lista paginada de clientes
-	 * 
-	 * @param nome
-	 * @param pageRequest
-	 * @return Page<Administrador>
-	 */
	Page<Administrador> buscarTodos(PageRequest pageRequest);
	
	/**
-	 * Cadastra um novo cliente na base de dados.
-	 * 
-	 * @param administrador
-	 * @return Administrador
-	 */
	Administrador persistir(Administrador administrador);

	/**
-	 * Remove um administrador da base de dados.
-	 * 
-	 * @param id
-	 */
	void remover(Long id);

}

