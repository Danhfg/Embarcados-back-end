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
	private long id_sensor_vaga;

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean ocupado;

	@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean preferencial;

	@Column(columnDefinition = "VARCHAR(50)")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "id_dispositivo")
	private Dispositivo dispositivo;

	public long getId_sensor_vaga() {
		return id_sensor_vaga;
	}

	public void setId_sensor_vaga(long id_sensor_vaga) {
		this.id_sensor_vaga = id_sensor_vaga;
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

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

}
