package br.com.imobiliaria.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.imobiliaria.api.entities.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome% ")
	Page<Cliente> findByNome(@Param("nome") String nome, Pageable pageable);

	Optional<Cliente> findByEmail(@Param("email") String email);
	
}
