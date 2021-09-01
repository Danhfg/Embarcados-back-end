package br.ufrn.dimap.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Universidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUniversidade;
	
	@Column(unique = true)
	private String nome;

	/*@OneToMany
	@JoinColumn(name = "idEstacionamentos")
	private List<Estacionamento> estacionamentos;*/

	public long getIdUniversidade() {
		return idUniversidade;
	}

	public void setIdUniversidade(long idUniversidade) {
		this.idUniversidade = idUniversidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
/*
	public List<Estacionamento> getEstacionamentos() {
		return estacionamentos;
	}

	public void setEstacionamentos(List<Estacionamento> estacionamentos) {
		this.estacionamentos = estacionamentos;
	}
*/
}
