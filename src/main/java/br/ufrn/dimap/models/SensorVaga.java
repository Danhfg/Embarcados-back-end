package br.ufrn.dimap.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SensorVaga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSensorVaga;

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean ocupado;

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean preferencial;

	@Column(columnDefinition = "VARCHAR(50)")
	private String status;
	
	/*@ManyToOne
	@JoinColumn(name = "idDispositivo")
	private Dispositivo dispositivo;*/	

	public long getIdSensorVaga() {
		return idSensorVaga;
	}

	public void setIdSensorVaga(long idSensorVaga) {
		this.idSensorVaga = idSensorVaga;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Boolean getPreferencial() {
		return preferencial;
	}

	public void setPreferencial(Boolean preferencial) {
		this.preferencial = preferencial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
/*
	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
*/
}
