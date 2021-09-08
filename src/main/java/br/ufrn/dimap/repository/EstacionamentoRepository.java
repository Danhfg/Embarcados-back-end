package br.ufrn.dimap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.dimap.models.Estacionamento;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long>{
    @Query(nativeQuery=true, value="SELECT * FROM estacionamento WHERE estacionamento.vagas_preferenciais > 0")
     List<Estacionamento> findAllWhereVagasPreferenciais();

    @Query(nativeQuery=true, value="SELECT * FROM estacionamento WHERE estacionamento.vagas_nao_preferenciais > 0")
     List<Estacionamento> findAllWhereVagasNaoPreferenciais();
    
    @Query(nativeQuery = true, value="SELECT * FROM estacionamento WHERE estacionamento.id_universidade = ?1")
    List<Estacionamento> findAllByUniversidadeId(Long idUniversidade);
    
     Optional<Estacionamento> findBySetor(String setor);

}
