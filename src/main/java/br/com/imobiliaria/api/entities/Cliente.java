package br.com.imobiliaria.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.imobiliaria.api.enums.PerfilEnum;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = -5754246207015712518L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo_cliente")
	private Long codigo;
	
	@Column(name = "nome_cliente", nullable = false)
	private String nome;
	
	@Column(name = "email_cliente", nullable = false)
	private String email;
	
	@Column(name = "senha_cliente", nullable = false)
	private String senha;
	
	@Column(name = "data_criacao_cliente", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao_cliente", nullable = false)
	private Date dataAtualizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil_cliente", nullable = false)
	private PerfilEnum perfil;
	
	@ManyToMany
	@JoinTable(name = "favorito_imovel_cliente",
	joinColumns = @JoinColumn(name = "codigo_cliente"),
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

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(Set<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", perfil=" + perfil
				+ ", imoveis=" + imoveis + "]";
	}

	
}
