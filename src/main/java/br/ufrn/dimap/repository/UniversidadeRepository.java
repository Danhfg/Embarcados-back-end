package br.ufrn.dimap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.dimap.models.Universidade;

import java.util.Optional;

public interface UniversidadeRepository extends JpaRepository<Universidade, Long>{
     Optional<Universidade> findByNome(String nome);
}
