package br.com.imobiliaria.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.api.entities.Administrador;
import br.com.imobiliaria.api.security.JwtUserFactory;
import br.com.imobiliaria.api.services.impl.AdministradorServiceImpl;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdministradorServiceImpl administradorServiceImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Administrador> administrador = administradorServiceImpl.buscarPorEmail(username);

		if (administrador.isPresent()) {
			return JwtUserFactory.create(administrador.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
