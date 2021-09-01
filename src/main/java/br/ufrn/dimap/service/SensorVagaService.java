package br.ufrn.dimap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.dimap.error.ResourceNotFoundException;
import br.ufrn.dimap.models.Dispositivo;
import br.ufrn.dimap.models.SensorVaga;
import br.ufrn.dimap.repository.SensorVagaRepository;

@Service
public class SensorVagaService {
	
	@Autowired
	private final SensorVagaRepository sensorVagaRepository;
	
	@Autowired
	private final DispositivoService dispositivoService;

	public SensorVagaService(SensorVagaRepository sensorVagaRepository, DispositivoService dispositivoService) {
		this.sensorVagaRepository = sensorVagaRepository;
		this.dispositivoService = dispositivoService;
	}

	public List<SensorVaga> listarSensores() {
		List<SensorVaga> listSensores = sensorVagaRepository.findAll();
		if(listSensores == null || listSensores.isEmpty()) {
			throw new ResourceNotFoundException("Nenhum sensor cadastrado");
		}
		return listSensores ;
	}

	public SensorVaga listarSensorPorId(long idSensorVaga) {
		if(sensorVagaRepository.findById(idSensorVaga).isPresent())
			return sensorVagaRepository.findById(idSensorVaga).get();
		else throw new ResourceNotFoundException("Nenhum sensor ocm id " + idSensorVaga + " encontrado!");
	}

	public SensorVaga cadastrarSensor(SensorVaga sensorVaga, long idDispositivo) {
		Dispositivo dispositivo = dispositivoService.listarDispositivosPorId(idDispositivo);
		//dispositivo.getSensores().add(sensorVaga);
		sensorVaga.setDispositivo(dispositivo);
		SensorVaga sensorSalvo = sensorVagaRepository.save(sensorVaga);
		return sensorSalvo;
	}	

}
