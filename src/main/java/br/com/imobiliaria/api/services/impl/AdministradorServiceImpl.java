
 package br.com.imobiliaria.api.services.impl;
 
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Administrador;
import br.com.imobiliaria.api.repositories.AdministradorRepository;
import br.com.imobiliaria.api.services.AdministradorService;

@Service
public class AdministradorServiceImpl implements AdministradorService{

	private static final Logger log = LoggerFactory.getLogger(AdministradorServiceImpl.class);
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Override
	public Optional<Administrador> buscarPorEmail(String email) {
		log.info("Buscando administradores por email {}");
		return this.administradorRepository.findByEmail(email);
	}

	@Override
	public Page<Administrador> buscarPorNome(String nome, PageRequest pageRequest) {
		log.info("Buscando administradores por nome {}");
		return this.administradorRepository.findByNome(nome, pageRequest);
	}

	@Override
	public Page<Administrador> buscarTodos(PageRequest pageRequest) {
		log.info("Buscando administradores {}");
		return this.administradorRepository.findAll(pageRequest);
	}

	@Override
	public Administrador persistir(Administrador administrador) {
		log.info("Persistindo administrador: {}", administrador);
		return this.administradorRepository.save(administrador);
	}

	@Override
	public Optional<Administrador> buscarPorCodigo(Long codigo) {
		log.info("Buscando administrador pelo IDl {}", codigo);
		return Optional.ofNullable(this.administradorRepository.findOne(codigo));
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o administrador ID {}", id);
		this.administradorRepository.delete(id);
	}

}

