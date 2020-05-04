package br.com.imobiliaria.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Pessoa;
import br.com.imobiliaria.api.repositories.PessoaRepository;
import br.com.imobiliaria.api.services.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService{

private static final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public Optional<Pessoa> buscarPorEmail(String email) {
		log.info("Buscando pessoa por email {}");
		return this.pessoaRepository.findByEmail(email);
	}

	@Override
	public Page<Pessoa> buscarPorNome(String nome, PageRequest pageRequest) {
		log.info("Buscando pessoa por nome {}");
		return this.pessoaRepository.findByNome(nome, pageRequest);
	}

	@Override
	public Page<Pessoa> buscarTodos(PageRequest pageRequest) {
		log.info("Buscando administradores {}");
		return this.pessoaRepository.findAll(pageRequest);
	}

	@Override
	public Pessoa persistir(Pessoa pessoa) {
		log.info("Persistindo pessoa: {}", pessoa);
		return this.pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> buscarPorCodigo(Long codigo) {
		log.info("Buscando pessoa pelo ID {}", codigo);
		return Optional.ofNullable(this.pessoaRepository.findOne(codigo));
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o pessoa ID {}", id);
		this.pessoaRepository.delete(id);
	}

}
