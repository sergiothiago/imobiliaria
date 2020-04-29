package br.com.imobiliaria.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.imobiliaria.api.enums.EspecificacaoEnum;
import br.com.imobiliaria.api.enums.StatusEnum;

@Entity
@Table(name = "imovel")
@Inheritance(strategy = InheritanceType.JOINED)
public class Imovel implements Serializable {
	
	private static final long serialVersionUID = -5254246207015712512L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo_imovel")
	private Long codigo;
	
	@Column(name = "referencia_imovel", nullable = false)
	private String referencia;
	
	@Column(name = "titulo_imovel", nullable = false)
	private String titulo;
	
	@Column(name = "corretor_imovel", nullable = true)
	private String corretor;
	
	@Column(name = "proprietario_imovel", nullable = true)
	private String proprietario;
	
	@Column(name = "descricao_imovel", nullable = false)
	private String descricao;
	
	@Column(name = "preco_imovel", nullable = false)
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_imovel", nullable = false)
	private StatusEnum status;
	
	@Column(name = "cep_imovel", nullable = false)
	private String cep;
	
	@Column(name = "numero_imovel", nullable = false)
	private String numero;
	
	@Column(name = "rua_imovel", nullable = false)
	private String rua;
	
	@Column(name = "bairro_imovel", nullable = false)
	private String bairro;
	
	@Column(name = "cidade_imovel", nullable = false)
	private String cidade;
	
	@Column(name = "area_imovel", nullable = false)
	private Long area;
	
	@Column(name = "destaque_imovel", nullable = false)
	private Boolean destaque;

	@Enumerated(EnumType.STRING)
	@Column(name = "especificacao_imovel", nullable = false)
	private EspecificacaoEnum especificacao;
	
	@Column(name = "data_criacao_imovel", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao_imovel", nullable = false)
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
    
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorretor() {
		return corretor;
	}

	public void setCorretor(String corretor) {
		this.corretor = corretor;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Boolean getDestaque() {
		return destaque;
	}

	public void setDestaque(Boolean destaque) {
		this.destaque = destaque;
	}

	public EspecificacaoEnum getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(EspecificacaoEnum especificacao) {
		this.especificacao = especificacao;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
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
