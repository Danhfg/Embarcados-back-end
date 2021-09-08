package br.ufrn.dimap.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Estacionamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEstacionamento;
	
	/*@OneToMany
	@JoinColumn(name = "id_dispositivo")
	private List<Dispositivo> dispositivos;*/
	
	@Column(columnDefinition = "VARCHAR(50)")
	private String setor;
	
	@Column(columnDefinition = "INT")
	private Integer vagasPreferenciais;
	
	@Column(columnDefinition = "INT")
	private Integer vagasNaoPreferenciais;
	
	@Column(columnDefinition = "DOUBLE")
	private Double latitude;
	
	@Column(columnDefinition = "DOUBLE")
	private Double longitude;
	
	@ManyToOne
	@JoinColumn(name = "id_universidade")
	private Universidade universidade;
	
	public Estacionamento() {
		
	}

	public long getIdEstacionamento() {
		return idEstacionamento;
	}

	public void setIdEstacionamento(long idEstacionamento) {
		this.idEstacionamento = idEstacionamento;
	}
/*
	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}
*/
	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Integer getVagasPreferenciais() {
		return vagasPreferenciais;
	}

	public void setVagasPreferenciais(Integer vagasPreferenciais) {
		this.vagasPreferenciais = vagasPreferenciais;
	}

	public Integer getVagasNaoPreferenciais() {
		return vagasNaoPreferenciais;
	}

	public void setVagasNaoPreferenciais(Integer vagasNaoPreferenciais) {
		this.vagasNaoPreferenciais = vagasNaoPreferenciais;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

}
