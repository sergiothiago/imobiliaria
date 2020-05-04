package br.com.imobiliaria.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Pessoa;
import br.com.imobiliaria.api.security.JwtUserFactory;
import br.com.imobiliaria.api.services.impl.PessoaServiceImpl;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaServiceImpl pessoaServiceImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Pessoa> pessoa = pessoaServiceImpl.buscarPorEmail(username);

		if (pessoa.isPresent()) {
			return JwtUserFactory.create(pessoa.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
