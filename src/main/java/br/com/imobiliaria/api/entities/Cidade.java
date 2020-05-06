package br.com.imobiliaria.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

	private static final long serialVersionUID = -5754246207015212528L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo_cidade")
	private Long codigo;

	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	@Column(name = "nome_cidade", nullable = false)
	private String nome;

	// TODO - AO ATUALIZAR BUGA POR CAUSA DO ORPHAN REMOVAL !!
	@JsonManagedReference
	@OneToMany(mappedBy = "cidade", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Bairro> bairros;

	@Column(name = "data_criacao_cidade", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao_cidade", nullable = false)
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
    }

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the bairros
	 */
	public List<Bairro> getBairros() {
		return bairros;
	}

	/**
	 * @param bairros the bairros to set
	 */
	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the dataAtualizacao
	 */
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	/**
	 * @param dataAtualizacao the dataAtualizacao to set
	 */
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

}
