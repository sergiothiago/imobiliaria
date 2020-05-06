package br.com.imobiliaria.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Cidade;
import br.com.imobiliaria.api.repositories.CidadeRepository;
import br.com.imobiliaria.api.services.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {

	private static final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public Optional<Cidade> buscarPorCodigo(Long codigo) {
		log.info("Buscando cidade pelo ID {}", codigo);
		return Optional.ofNullable(this.cidadeRepository.findOne(codigo));
	}

	@Override
	public List<Cidade> buscarTodos() {
		log.info("Persistindo cidade: {}");
		return this.cidadeRepository.findAll();
	}

	@Override
	public Cidade persistir(Cidade cidade) {
		log.info("Persistindo cidade: {}", cidade);
		return this.cidadeRepository.save(cidade);
	}

	@Override
	public void remover(Long id) {
		log.info("Persistindo cidade: {}", id);
		 this.cidadeRepository.delete(id);
	}

}
