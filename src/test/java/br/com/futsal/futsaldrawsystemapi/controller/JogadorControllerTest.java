package br.com.futsal.futsaldrawsystemapi.controller;

import br.com.futsal.futsaldrawsystemapi.dto.JogadorDTO;
import br.com.futsal.futsaldrawsystemapi.dto.SorteioRequestDTO;
import br.com.futsal.futsaldrawsystemapi.service.JogadorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class JogadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JogadorService jogadorService;

    @Test
    @DisplayName("Retornar 201 ao cadastrar novo jogador ")
    void CadastrarJogador() throws Exception {
        JogadorDTO dto = new JogadorDTO();
        dto.setNome("ThomZão");

        mockMvc.perform(post("/jogador/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("ThomZão"));
    }

    @Test
    @DisplayName("Retorna lista de jogadores e status 200")
    void listarJogadores() throws Exception {

        JogadorDTO dto = new JogadorDTO();
        dto.setNome("Falcão");

        jogadorService.cadastrarJogador(dto);


        mockMvc.perform(get("/jogador/listar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Falcão"));
    }


    @Test
    @DisplayName("Deve deletar um jogador existente e retornar 204")
    void deveDeletarJogador() throws Exception {
        JogadorDTO dto = new JogadorDTO();
        dto.setNome("Jogador para Deletar");
        var jogadorSalvo = jogadorService.cadastrarJogador(dto);
        Long idExistente = jogadorSalvo.getId();


        mockMvc.perform(delete("/jogador/{id}", idExistente)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("Deve sortear times misturando jogadores fixos e visitantes")
    void deveSortearTimesComSucesso() throws Exception {

        JogadorDTO j1 = new JogadorDTO();
        j1.setNome("Fixo 1");
        JogadorDTO j2 = new JogadorDTO();
        j2.setNome("Fixo 2");

        var salvo1 = jogadorService.cadastrarJogador(j1);
        var salvo2 = jogadorService.cadastrarJogador(j2);

        SorteioRequestDTO request = SorteioRequestDTO.builder()
                .idsJogadoresFixos(List.of(salvo1.getId(), salvo2.getId()))
                .nomesVisitantes(List.of("Visitante A", "Visitante B"))
                .jogadoresPorTime(2)
                .build();

        mockMvc.perform(post("/jogador/sortear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].length()").value(2));
    }


}
