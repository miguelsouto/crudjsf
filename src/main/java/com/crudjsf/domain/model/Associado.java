package com.crudjsf.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "associado")
public class Associado {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull
	private String nome;
	
	@Column
	@NotNull
	private String rg;
	
	@Column
	@NotNull
	private String cpf;
	
	@Column
	private String telefone;
	
	@Column
	private String endereco;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idContaBancaria")
	private ContaBancaria contaBancaria;

	@Column
	@Enumerated(EnumType.STRING)
	private TipoAssociado tipoAssociado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idResponsavel", insertable = true, updatable = true, nullable = true)
	private Associado responsavel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoAssociado getTipoAssociado() {
		return tipoAssociado;
	}

	public void setTipoAssociado(TipoAssociado tipoAssociado) {
		this.tipoAssociado = tipoAssociado;
	}

	public Associado getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Associado responsavel) {
		this.responsavel = responsavel;
	}
	
	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associado other = (Associado) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Associado [nome=" + nome + "]";
	}	
}
