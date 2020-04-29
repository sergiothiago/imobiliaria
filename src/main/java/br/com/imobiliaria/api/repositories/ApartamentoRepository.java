package br.com.imobiliaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.imobiliaria.api.entities.Apartamento;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long>{

}
