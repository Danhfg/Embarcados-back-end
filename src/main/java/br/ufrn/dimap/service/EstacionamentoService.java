package br.ufrn.dimap.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.dimap.error.ResourceNotFoundException;
import br.ufrn.dimap.models.Dispositivo;
import br.ufrn.dimap.models.Estacionamento;
import br.ufrn.dimap.models.Universidade;
import br.ufrn.dimap.repository.EstacionamentoRepository;

@Service
public class EstacionamentoService {
	
	@Autowired
	private final EstacionamentoRepository estacionamentoRepository;
	
	@Autowired
	private final UniversidadeService universidadeService;

	@Autowired
	public EstacionamentoService(EstacionamentoRepository estacionamentoRepository, UniversidadeService universidadeService) {
		this.estacionamentoRepository = estacionamentoRepository;
		this.universidadeService = universidadeService;
	}
	
	public List<Estacionamento> listarEstacionamentosPorUniversidade(long idUniversidade){
		return estacionamentoRepository.findAllByUniversidadeId(idUniversidade);
	}


	public Estacionamento listarEstacionamentoPorId(long idEstacionamento) {
		if (estacionamentoRepository.findById(idEstacionamento).isPresent()) 
			return estacionamentoRepository.findById(idEstacionamento).get();
		else throw new ResourceNotFoundException("Nenhum Estacionamento com id " + " encontrado!");
	}
/*
	@Transactional
	public void adicionarDispositivo(long idEstacionamento, Dispositivo dispositivo)
	{
		if (estacionamentoRepository.findById(idEstacionamento).isPresent())
		{
			Estacionamento estacionamento = estacionamentoRepository.findById(idEstacionamento).get();
			//estacionamento.getDispositivos().add(dispositivo);
			estacionamentoRepository.save(estacionamento);
			
		}
		else throw new ResourceNotFoundException("Nenhum Estacionamento com id " + " encontrado!");
	}
*/
	public List<Estacionamento> listarEstacionamentos() {
		List<Estacionamento> estacionamentos = estacionamentoRepository.findAll();
		if(estacionamentos == null || estacionamentos.isEmpty())
			throw new ResourceNotFoundException("Nenhum estacionamento cadastrado");
		else return estacionamentos;
	}

	@Transactional
	public Estacionamento cadastrarEstacionamento(long idUniversidade, Estacionamento estacionamento) {
		Universidade universidade = universidadeService.listarUniversidadPorId(idUniversidade);
		estacionamento.setUniversidade(universidade);
		return estacionamentoRepository.save(estacionamento);
	}

}
