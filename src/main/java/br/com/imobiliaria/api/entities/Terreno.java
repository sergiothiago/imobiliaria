package br.com.imobiliaria.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "terreno")
@PrimaryKeyJoinColumn(name = "codigo_imovel")
public class Terreno extends Imovel implements Serializable {
	
	private static final long serialVersionUID = -5754246207225712512L;
	
	@Column(name = "grau_inclinacao_terreno", nullable = false)
	private Long grauDeInclinacao;

	public Long getGrauDeInclinacao() {
		return grauDeInclinacao;
	}

	public void setGrauDeInclinacao(Long grauDeInclinacao) {
		this.grauDeInclinacao = grauDeInclinacao;
	}

}
