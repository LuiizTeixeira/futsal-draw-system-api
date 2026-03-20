package br.com.futsal.futsaldrawsystemapi.service;

import br.com.futsal.futsaldrawsystemapi.dto.JogadorDTO;
import br.com.futsal.futsaldrawsystemapi.exception.ApiException;
import br.com.futsal.futsaldrawsystemapi.model.Jogador;
import br.com.futsal.futsaldrawsystemapi.repository.JogadorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class JogadorServiceTest {

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Test
    @DisplayName("Salvar jogador se dados for valido")
    void salvarJogador() {
        JogadorDTO dto = new JogadorDTO();
        dto.setNome("Luiz");

        Jogador salvo = jogadorService.cadastrarJogador(dto);

        assertNotNull(salvo.getId());
        assertEquals("Luiz", salvo.getNome());
        assertTrue(jogadorRepository.existsByNome("Luiz"));
    }

    @Test
    @DisplayName("Erro Jogador Existente")
    void JogadorDuplicado() {
        JogadorDTO dto = new JogadorDTO();
        dto.setNome("Danilo");

        jogadorService.cadastrarJogador(dto);

        ApiException exception = assertThrows(ApiException.class, () -> {
            jogadorService.cadastrarJogador(dto);
        });

        assertEquals("Jogador já cadastrado", exception.getMessage());
    }
}
