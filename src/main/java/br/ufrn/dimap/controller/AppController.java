package br.ufrn.dimap.controller;


import br.ufrn.dimap.error.ResourceNotFoundException;
import br.ufrn.dimap.error.VagasIndisponiveisException;
import br.ufrn.dimap.models.Estacionamento;
import br.ufrn.dimap.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/app-service")
public class AppController {

    @Autowired
    AppService appService;


    @GetMapping(value="/vagas-proximas")
    public ResponseEntity<?> getVagasProximas(@RequestBody double latitude,@RequestBody double longitude,
                                             @RequestBody boolean preferencial){
        Estacionamento estacionamento = new Estacionamento();
        try{
            estacionamento = appService.getVagaProxima(latitude, longitude, preferencial);
        } catch (VagasIndisponiveisException e) {
            return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
        }

        return new ResponseEntity<Estacionamento>(estacionamento, HttpStatus.OK);
    }

    @GetMapping(value="/localizar-estacionamento")
    public ResponseEntity<?> localizarEstacionamento(@RequestBody String setor){
        Estacionamento estacionamento = new Estacionamento();
        try{
            estacionamento = appService.localizarEstacionamento(setor);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Estacionamento>(estacionamento, HttpStatus.OK);
    }

    @PostMapping(value="/alterar-status-sensor/{id}")
    public ResponseEntity<?> alterarStatusSensor(@PathVariable long id,
                                                 @RequestBody String status){
        try{
            appService.alterarStatusSensor(id,status);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/alterar-status-dispositivo/{id}")
    public ResponseEntity<?> alterarStatusDispositivo(@PathVariable long id,
                                                 @RequestBody String status){
        try{
            appService.alterarStatusDispositivo(id,status);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/listar-estacionamentos")
    public ResponseEntity<?> listarEstacionamentos(@RequestBody String nomeUniversidade){
        List<Estacionamento> estacionamentos = new ArrayList<>();
        try{
            estacionamentos = appService.listarEstacionamentosPorUniversidade(nomeUniversidade);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(estacionamentos, HttpStatus.OK);
    }
    
    @GetMapping(value="gerar-relatorio")
    public ResponseEntity<?> gerarUrlRelatorio(@RequestBody LocalDate inicio,@RequestBody LocalDate fim,
            @RequestBody long idEstacionamento)
    {
    	return new ResponseEntity<>("https://estacionamentos.ufrn.edu.br/relatorio"+idEstacionamento, HttpStatus.OK);
    }
}
