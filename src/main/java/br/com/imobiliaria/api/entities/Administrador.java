package br.com.imobiliaria.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.imobiliaria.api.enums.PerfilEnum;

@Entity
@Table(name = "administrador")
public class Administrador implements Serializable {
	
	private static final long serialVersionUID = -5754246207015312512L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo_administrador")
	private Long codigo;
	
	@Column(name = "nome_administrador", nullable = false)
	private String nome;
	
	@Column(name = "email_administrador", nullable = false)
	private String email;
	
	@Column(name = "senha_administrador", nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil_administrador", nullable = false)
	private PerfilEnum perfil;
	
	@Column(name = "data_criacao_administrador", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao_administrador", nullable = false)
	private Date dataAtualizacao;

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

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
    
}
