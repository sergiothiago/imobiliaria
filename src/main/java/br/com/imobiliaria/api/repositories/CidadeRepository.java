package br.com.imobiliaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.imobiliaria.api.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
