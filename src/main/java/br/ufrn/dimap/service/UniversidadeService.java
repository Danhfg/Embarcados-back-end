package br.ufrn.dimap.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.dimap.error.ResourceNotFoundException;
import br.ufrn.dimap.models.Estacionamento;
import br.ufrn.dimap.models.Universidade;
import br.ufrn.dimap.repository.UniversidadeRepository;

@Service
public class UniversidadeService {
	
	@Autowired
	private final UniversidadeRepository universidadeRepository;

	public UniversidadeService(UniversidadeRepository universidadeRepository) {
		this.universidadeRepository = universidadeRepository;
	}

	public List<Universidade> listarUniversidades() {
		List<Universidade> universidades = universidadeRepository.findAll();
		if(universidades == null || universidades.isEmpty())
			throw new ResourceNotFoundException("Nenhuma universidade cadastrada!");
		return universidades;
	}

	public Universidade listarUniversidadPorId(long idUniversidade) {
		if(universidadeRepository.findById(idUniversidade).isPresent())
			return universidadeRepository.findById(idUniversidade).get();
		else throw new ResourceNotFoundException("Nenhuma universidade com id " + " encontrada!");
	}

	public Universidade cadastrarUniversidade(Universidade universidade) {
		return universidadeRepository.save(universidade);
				
	}
	
	public void adicionarEstacionamento(long idUniversidade, Estacionamento estacionamento) {
		if(universidadeRepository.findById(idUniversidade).isPresent())
		{
			Universidade universidade = universidadeRepository.findById(idUniversidade).get();
			universidade.getEstacionamentos().add(estacionamento);
			universidadeRepository.save(universidade);
		}
		else throw new ResourceNotFoundException("Nenhuma universidade com id " + " encontrada!");
	}

}
