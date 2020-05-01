package br.com.imobiliaria.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.imobiliaria.api.entities.Administrador;
import br.com.imobiliaria.api.enums.PerfilEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um administrador.
	 * 
	 * @param administrador
	 * @return JwtUser
	 */
	public static JwtUser create(Administrador administrador) {
		return new JwtUser(administrador.getCodigo(), administrador.getEmail(), administrador.getSenha(),
				mapToGrantedAuthorities(administrador.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

}
