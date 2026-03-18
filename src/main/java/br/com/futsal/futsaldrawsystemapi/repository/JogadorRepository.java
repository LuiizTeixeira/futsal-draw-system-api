package br.com.futsal.futsaldrawsystemapi.repository;

import br.com.futsal.futsaldrawsystemapi.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Boolean existsByNome(String nome);
}
