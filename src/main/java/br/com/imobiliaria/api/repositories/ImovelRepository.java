package br.com.imobiliaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.imobiliaria.api.entities.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long>{
	
}
