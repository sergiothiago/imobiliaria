package br.com.imobiliaria.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Cliente;
import br.com.imobiliaria.api.repositories.ClienteRepository;
import br.com.imobiliaria.api.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Optional<Cliente> buscarPorEmail(String email) {
		log.info("Buscando clientes por email {}");
		return this.clienteRepository.findByEmail(email);
	}

	@Override
	public Page<Cliente> buscarPorNome(String nome, PageRequest pageRequest) {
		log.info("Buscando clientes por nome {}");
		return this.clienteRepository.findByNome(nome, pageRequest);
	}

	@Override
	public Page<Cliente> buscarTodos(PageRequest pageRequest) {
		log.info("Buscando clientes {}");
		return this.clienteRepository.findAll(pageRequest);
	}

	@Override
	public Cliente persistir(Cliente cliente) {
		log.info("Persistindo cliente: {}", cliente);
		return this.clienteRepository.save(cliente);
	}

	@Override
	public Optional<Cliente> buscarPorCodigo(Long codigo) {
		log.info("Buscando cliente pelo IDl {}", codigo);
		return Optional.ofNullable(this.clienteRepository.findOne(codigo));
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o client ID {}", id);
		this.clienteRepository.delete(id);
	}

}
