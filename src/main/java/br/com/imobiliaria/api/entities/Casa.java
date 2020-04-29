package br.com.imobiliaria.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "casa")
@PrimaryKeyJoinColumn(name = "codigo_imovel")
public class Casa extends Imovel implements Serializable {
	
	private static final long serialVersionUID = -5344246207015712512L;

	@Column(name = "quantidade_quartos_casa", nullable = false)
	private Long quantidadeQuartos;
	
	@Column(name = "quantidade_salas_casa", nullable = false)
	private Long quantidadeSalas;
	
	@Column(name = "quantidade_cozinhas_casa", nullable = false)
	private Long quantidadeCozinhas;
	
	@Column(name = "quantidade_banheiros_casa", nullable = false)
	private Long quantidadeBanheiros;

	@Column(name = "quantidade_garagens_casa", nullable = false)
	private Long quantidadeGaragens;

	public Long getQuantidadeQuartos() {
		return quantidadeQuartos;
	}

	public void setQuantidadeQuartos(Long quantidadeQuartos) {
		this.quantidadeQuartos = quantidadeQuartos;
	}

	public Long getQuantidadeSalas() {
		return quantidadeSalas;
	}

	public void setQuantidadeSalas(Long quantidadeSalas) {
		this.quantidadeSalas = quantidadeSalas;
	}

	public Long getQuantidadeCozinhas() {
		return quantidadeCozinhas;
	}

	public void setQuantidadeCozinhas(Long quantidadeCozinhas) {
		this.quantidadeCozinhas = quantidadeCozinhas;
	}

	public Long getQuantidadeBanheiros() {
		return quantidadeBanheiros;
	}

	public void setQuantidadeBanheiros(Long quantidadeBanheiros) {
		this.quantidadeBanheiros = quantidadeBanheiros;
	}

	public Long getQuantidadeGaragens() {
		return quantidadeGaragens;
	}

	public void setQuantidadeGaragens(Long quantidadeGaragens) {
		this.quantidadeGaragens = quantidadeGaragens;
	}
	
	
}
