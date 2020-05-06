package br.com.imobiliaria.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Bairro;
import br.com.imobiliaria.api.repositories.BairroRepository;
import br.com.imobiliaria.api.services.BairroService;

@Service
public class BairroServiceImpl implements BairroService {

	private static final Logger log = LoggerFactory.getLogger(BairroServiceImpl.class);

	@Autowired
	private BairroRepository bairroRepository;
	
	@Override
	public Optional<Bairro> buscarPorCodigo(Long codigo) {
		log.info("Buscando bairro pelo ID {}", codigo);
		return Optional.ofNullable(this.bairroRepository.findOne(codigo));
	}

	@Override
	public List<Bairro> buscarTodos() {
		log.info("Buscando todos bairros");
		return this.bairroRepository.findAll();
	}

	@Override
	public Bairro persistir(Bairro bairro) {
		log.info("cadastrando bairro");
		return this.bairroRepository.save(bairro);
	}

	@Override
	public void remover(Long id) {
		log.info("remover bairro");
		this.bairroRepository.delete(id);
		
	}
	
}
