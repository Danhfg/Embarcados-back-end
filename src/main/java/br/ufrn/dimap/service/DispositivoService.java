package br.ufrn.dimap.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.dimap.error.ResourceNotFoundException;
import br.ufrn.dimap.models.Dispositivo;
import br.ufrn.dimap.models.Estacionamento;
import br.ufrn.dimap.models.SensorVaga;
import br.ufrn.dimap.repository.DispositivoRepository;

@Service
public class DispositivoService {
	
	@Autowired
	private final DispositivoRepository dispositivoRepository;
	
	@Autowired
	private final EstacionamentoService estacionamentoService;

	@Autowired
	public DispositivoService(DispositivoRepository dispositivoRepository, EstacionamentoService estacionamentoService) {
		this.dispositivoRepository = dispositivoRepository;
		this.estacionamentoService = estacionamentoService;
	}
	
	public List<Dispositivo> listarDispositivos(){
		List<Dispositivo> listDisp = dispositivoRepository.findAll();
		if (listDisp == null || listDisp.isEmpty())
		{
			throw new ResourceNotFoundException("Nenhum dispositivo cadastrado");
		}
		return listDisp;
	}

	public Dispositivo listarDispositivosPorId(long idDispositivo) {
		if (dispositivoRepository.findById(idDispositivo).isPresent())
			return dispositivoRepository.findById(idDispositivo).get();
		else
			throw new ResourceNotFoundException("Nenhum dispositivo com id " + " encontrado!");
	}

	@Transactional
	public Dispositivo cadastrar(long idEstacionamento, Dispositivo dispositivo) {
		Dispositivo dipositivoSalvo = dispositivoRepository.save(dispositivo);
		estacionamentoService.adicionarDispositivo(idEstacionamento, dipositivoSalvo);
		return dipositivoSalvo;
	}
	
	@Transactional
	public void adicionarSensor(long idDispositivo, SensorVaga sensorvaga)
	{
		if(dispositivoRepository.findById(idDispositivo).isPresent())
		{
			Dispositivo disp = dispositivoRepository.findById(idDispositivo).get();
			disp.getSensores().add(sensorvaga);
			dispositivoRepository.save(disp);
		}
		else throw new ResourceNotFoundException("Nenhum Dispositivo com id " + "encontrado!");
	}

}
