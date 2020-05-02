package br.com.imobiliaria.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.imobiliaria.api.entities.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long>{

	Optional<Imovel> findByReferencia(@Param("referencia") String referencia);
}
