package br.com.imobiliaria.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.imobiliaria.api.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %:nome% ")
	Page<Pessoa> findByNome(@Param("nome") String nome, Pageable pageable);

	Optional<Pessoa> findByEmail(@Param("email") String email);
}
