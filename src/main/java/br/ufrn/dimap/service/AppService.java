package br.ufrn.dimap.service;

import br.ufrn.dimap.error.ResourceNotFoundException;
import br.ufrn.dimap.error.VagasIndisponiveisException;
import br.ufrn.dimap.models.Dispositivo;
import br.ufrn.dimap.models.Estacionamento;
import br.ufrn.dimap.models.SensorVaga;
import br.ufrn.dimap.models.Universidade;
import br.ufrn.dimap.repository.DispositivoRepository;
import br.ufrn.dimap.repository.EstacionamentoRepository;
import br.ufrn.dimap.repository.SensorVagaRepository;
import br.ufrn.dimap.repository.UniversidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {

    @Autowired
    EstacionamentoRepository estacionamentoRepository;

    @Autowired
    DispositivoRepository dispositivoRepository;

    @Autowired
    SensorVagaRepository sensorVagaRepositor;

    @Autowired
    UniversidadeRepository universidadeRepository;

    double distanciaDoisPontos(double xPonto1, double yPonto1,double xPonto2, double yPonto2){
        return  Math.sqrt( Math.pow((xPonto1 - xPonto2),2) + Math.pow((yPonto1 - yPonto2),2) );
    }

    public Estacionamento getVagaProxima(double latitude, double longitude, boolean preferencial) throws VagasIndisponiveisException {
        Estacionamento estacionamentoMaisProximo = new Estacionamento();
        List<Estacionamento> estacionamentoList = new ArrayList<>();
        if (preferencial){
            estacionamentoList = estacionamentoRepository.findAllWhereVagasPreferenciais();
        } else {
            estacionamentoRepository.findAllWhereVagasNaoPreferenciais();
        }

        if (estacionamentoList.isEmpty()){
            throw new VagasIndisponiveisException("Não há vagas disponíveis", HttpStatus.NOT_FOUND);
        }

        for ( Estacionamento e: estacionamentoList ) {
            if ( estacionamentoMaisProximo == null ) {
                estacionamentoMaisProximo = e;

            } else if ( distanciaDoisPontos(latitude, e.getLatitude(), longitude, e.getLongitude())
                    <= distanciaDoisPontos(latitude, estacionamentoMaisProximo.getLatitude(),
                    longitude, estacionamentoMaisProximo.getLongitude()) ) {
                estacionamentoMaisProximo = e;
            }
        }


        return estacionamentoMaisProximo;
    }

    public Estacionamento localizarEstacionamento(String setor) throws ResourceNotFoundException {
        Optional<Estacionamento> estacionamento = estacionamentoRepository.findBySetor(setor);
        if(estacionamento.isEmpty()){
            throw new ResourceNotFoundException("Estacionamento não encontrado");
        }
        return estacionamento.get();
    }

    public void alterarStatusSensor(long id, String status) throws ResourceNotFoundException
    {
        Optional<SensorVaga> sensorVaga = sensorVagaRepositor.findById(id);
        if (sensorVaga.isEmpty()) {
            throw new ResourceNotFoundException("Sensor não encontrado");
        }

        sensorVaga.get().setStatus(status);
        sensorVagaRepositor.save(sensorVaga.get());
    }

    public void alterarStatusDispositivo(long id, String status) throws ResourceNotFoundException
    {
        Optional<Dispositivo> dispositivo = dispositivoRepository.findById(id);
        if (dispositivo.isEmpty()) {
            throw new ResourceNotFoundException("Dispositivo não encontrado");
        }

        dispositivo.get().setStatus(status);
        dispositivoRepository.save(dispositivo.get());
    }
    public List<Estacionamento> listarEstacionamentosPorUniversidade(String nomeUniversidade) throws ResourceNotFoundException{
        Optional<Universidade> universidade = universidadeRepository.findByNome(nomeUniversidade);
        if(universidade.isEmpty()){
            throw new ResourceNotFoundException("Universidade não encontrada");
        }
        return new ArrayList<>();
//        return universidade.get().getEstacionamentos();

    }
    /*public String gerarUrlRelatorio(Date dataInicio, Date dataFim, long idEstacionamento){

    }*/

}
