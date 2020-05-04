package br.com.imobiliaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.imobiliaria.api.entities.Bairro;

public interface BairroRepository extends JpaRepository<Bairro, Long>{

}
