package br.com.imobiliaria.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.imobiliaria.api.entities.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long>{

	Optional<Imovel> findByReferencia(@Param("referencia") String referencia);

	@Query("SELECT i FROM Imovel i WHERE i.titulo LIKE %:param% ")
	Page<Imovel> buscaSimples(@Param("param") String param, Pageable pageable);

}
