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

import br.ufrn.dimap.models.Estacionamento;
import br.ufrn.dimap.service.EstacionamentoService;

@RestController
@RequestMapping(value = "/api")
public class EstacionamentoController {
	
	@Autowired
	private final EstacionamentoService estacionamentoService;

	@Autowired
	public EstacionamentoController(EstacionamentoService estacionamentoService) {
		this.estacionamentoService = estacionamentoService;
	}
	
	@GetMapping(value = "/estacionamentos")
	public ResponseEntity<?> listarEstacionamentos()
	{
		List<Estacionamento> estacionamentos = estacionamentoService.listarEstacionamentos();
		return new ResponseEntity<>(estacionamentos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/estacionamento/{idEstacionamento}")
	public ResponseEntity<?> listarEstacionamentoPorId(@PathVariable long idEstacionamento)
	{
		Estacionamento estacionamento = estacionamentoService.listarEstacionamentoPorId(idEstacionamento);
		return new ResponseEntity<>(estacionamento, HttpStatus.OK);
	}
	
	@PostMapping(value = "/estacionamento/cadastrar/{idUniversidade}")
	public ResponseEntity<?> cadastrarEstacionamento(@PathVariable long idUniversidade, @RequestBody Estacionamento estacionamento)
	{
		Estacionamento estacionamentoSalvo = estacionamentoService.cadastrarEstacionamento(idUniversidade, estacionamento);
		return new ResponseEntity<>(estacionamentoSalvo, HttpStatus.OK);
		
	}

}
