package br.com.imobiliaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.imobiliaria.api.entities.Casa;

public interface CasaRepository extends JpaRepository<Casa, Long>{

}
