package br.ufrn.dimap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.dimap.models.SensorVaga;
import br.ufrn.dimap.service.SensorVagaService;

@RestController
@RequestMapping(value = "/api")
public class SersorVagaController {
	
	@Autowired
	private final SensorVagaService sensorVagaService;

	public SersorVagaController(SensorVagaService sensorVagaService) {
		this.sensorVagaService = sensorVagaService;
	}
	
	@GetMapping(value = "/sensorVaga")
	public ResponseEntity<?> listarSensores(){
		List<SensorVaga> listSensores = sensorVagaService.listarSensores();
		return new ResponseEntity<>(listSensores, HttpStatus.OK);
	}
	
	@GetMapping(value = "/sensorVaga/{idSensorVaga}")
	public ResponseEntity<?> listarSensores(@PathVariable long idSensorVaga){
		SensorVaga sensor = sensorVagaService.listarSensorPorId(idSensorVaga);
		return new ResponseEntity<>(sensor, HttpStatus.OK);
	}
	
	@PostMapping(value = "/sensorVaga/cadastrar/{idDispositivo}")
	public ResponseEntity<?> cadastrarSensor(@RequestBody SensorVaga sensorVaga, @PathVariable long idDispositivo){
		SensorVaga sensor = sensorVagaService.cadastrarSensor(sensorVaga, idDispositivo);
		return new ResponseEntity<>(sensor, HttpStatus.OK);
	}

}
