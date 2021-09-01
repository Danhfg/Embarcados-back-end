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
public class Dispositivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDispositivo;
	
	@OneToMany
	@JoinColumn(name = "idSensorVaga")
	private List<SensorVaga> sensores;

	@Column(columnDefinition = "VARCHAR(50)")
	private String status;
	
	/*@ManyToOne
	@JoinColumn(name = "idEstacionamento")
	private Estacionamento estacionamento;*/

	public long getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public List<SensorVaga> getSensores() {
		return sensores;
	}

	public void setSensores(List<SensorVaga> sensores) {
		this.sensores = sensores;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
/*
	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}
*/
}
