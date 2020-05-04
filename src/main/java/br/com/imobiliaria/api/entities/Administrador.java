package br.com.imobiliaria.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.imobiliaria.api.enums.PerfilEnum;

@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name = "codigo_pessoa")
public class Administrador extends Pessoa implements Serializable  {

	private static final long serialVersionUID = -5752341207015312512L;

	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
        perfil = PerfilEnum.ROLE_ADMIN;
    }

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	
}
