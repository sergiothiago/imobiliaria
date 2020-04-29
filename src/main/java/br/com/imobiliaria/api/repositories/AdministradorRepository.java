package br.com.imobiliaria.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.imobiliaria.api.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	@Query("SELECT a FROM Administrador a WHERE a.nome LIKE %:nome% ")
	Page<Administrador> findByNome(@Param("nome") String nome, Pageable pageable);

	Optional<Administrador> findByEmail(@Param("email") String email);
}
