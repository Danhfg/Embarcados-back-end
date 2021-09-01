package br.ufrn.dimap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.dimap.models.Dispositivo;
import br.ufrn.dimap.service.DispositivoService;

@RestController
@RequestMapping(value = "/api")
public class DispositivoController {

	@Autowired
	private final DispositivoService dispositivoService;

	@Autowired
	public DispositivoController(DispositivoService dispositivoService) {
		this.dispositivoService = dispositivoService;
	}
	
	@GetMapping(value = "/dispositivos")
	public ResponseEntity<?> listarDispositivos(){
		List<Dispositivo> listDisp = dispositivoService.listarDispositivos();
		return new ResponseEntity<>(listDisp, HttpStatus.OK);
	}
	
	@GetMapping(value = "/dispositivo/{idDispositivo}")
	public ResponseEntity<?> listarDispositivosPorId(@PathVariable long idDispositivo){
		Dispositivo disp = dispositivoService.listarDispositivosPorId(idDispositivo);
		return new ResponseEntity<>(disp, HttpStatus.OK);
	}
	
	@PostMapping(value = "/dispositivo/cadastrar/{idEstacionamento}")
	public ResponseEntity<?> cadastrarDispositivo(@PathVariable long idEstacionamento, @RequestBody Dispositivo dispositivo)
	{
		dispositivoService.cadastrar(idEstacionamento, dispositivo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
