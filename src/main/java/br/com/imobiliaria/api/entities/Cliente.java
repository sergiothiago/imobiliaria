package br.com.imobiliaria.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.imobiliaria.api.enums.PerfilEnum;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "codigo_pessoa")
public class Cliente extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = -5754246207015712518L;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "favorito_imovel_cliente",
	joinColumns = @JoinColumn(name = "codigo_pessoa"),
	inverseJoinColumns = @JoinColumn(name = "codigo_imovel"))
	private Set<Imovel> imoveis = new HashSet<>();

	
	
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
        perfil = PerfilEnum.ROLE_CLIENTE;
    }

	/**
	 * @return the imoveis
	 */
	public Set<Imovel> getImoveis() {
		return imoveis;
	}

	/**
	 * @param imoveis the imoveis to set
	 */
	public void setImoveis(Set<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
