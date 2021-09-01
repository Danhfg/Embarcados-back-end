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

import br.ufrn.dimap.models.Universidade;
import br.ufrn.dimap.service.UniversidadeService;

@RestController
@RequestMapping(value = "/api")
public class UniversidadeController {
	
	@Autowired
	private final UniversidadeService universidadeService;

	@Autowired
	public UniversidadeController(UniversidadeService universidadeService) {
		this.universidadeService = universidadeService;
	}
	
	@GetMapping(value = "/universidades")
	public ResponseEntity<?> listarUniversidades()
	{
		List<Universidade> universidades = universidadeService.listarUniversidades();
		return new ResponseEntity<>(universidades, HttpStatus.OK);		
	}
	
	@GetMapping(value = "/universidade/{idUniversidade}")
	public ResponseEntity<?> listarUniversidadPorId(@PathVariable long idUniversidade)
	{
		Universidade universidade = universidadeService.listarUniversidadPorId(idUniversidade);
		return new ResponseEntity<>(universidade, HttpStatus.OK);		
	}
	
	@PostMapping(value = "/universidade/cadastrar")
	public ResponseEntity<?> cadastrarUniversidade(@RequestBody Universidade universidade)
	{
		Universidade universidadeSalva = universidadeService.cadastrarUniversidade(universidade);
		return new ResponseEntity<>(universidadeSalva,HttpStatus.OK);		
		
	}
	

}
