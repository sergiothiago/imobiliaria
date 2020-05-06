package br.com.imobiliaria.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Imovel;
import br.com.imobiliaria.api.repositories.ImovelRepository;
import br.com.imobiliaria.api.services.ImovelService;

@Service
public class ImovelServiceImpl implements ImovelService{

	private static final Logger log = LoggerFactory.getLogger(ImovelServiceImpl.class);

	@Autowired
	private ImovelRepository imovelRepository;
		
	@Override
	public Imovel persistir(Imovel imovel) {
		log.info("Persistindo imovel: {}", imovelRepository);
		return this.imovelRepository.save(imovel);
	}

	@Override
	public Page<Imovel> buscarTodos(PageRequest pageRequest) {
		log.info("Buscando imovel");
		return this.imovelRepository.findAll(pageRequest);
	}

	@Override
	public Optional<Imovel> buscarPorReferencia(String referencia) {
		log.info("Buscando imovel por referencia {}", referencia);
		return this.imovelRepository.findByReferencia(referencia);
	}

	@Override
	public Optional<Imovel> buscarPorCodigo(Long codigo) {
		log.info("Buscando imovel por codigo {}", codigo);
		return Optional.ofNullable(this.imovelRepository.findOne(codigo));
	}

	@Override
	public void remover(Long codigo) {
		log.info("Removendo imovel por codigo {}", codigo);
		this.imovelRepository.delete(codigo);
	}

	@Override
	public Page<Imovel> buscaSimples(String param, PageRequest pageRequest) {
		log.info("Busca simples");
		return this.imovelRepository.buscaSimples(param, pageRequest);
	}

}
