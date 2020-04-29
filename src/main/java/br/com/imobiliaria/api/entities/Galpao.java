package br.com.imobiliaria.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "galpao")
@PrimaryKeyJoinColumn(name = "codigo_imovel")
public class Galpao extends Imovel implements Serializable {
	
	private static final long serialVersionUID = -5754247707015712512L;

}
