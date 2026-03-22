package br.com.futsal.futsaldrawsystemapi.service;

import br.com.futsal.futsaldrawsystemapi.dto.JogadorDTO;
import br.com.futsal.futsaldrawsystemapi.dto.SorteioRequestDTO;
import br.com.futsal.futsaldrawsystemapi.exception.ApiException;
import br.com.futsal.futsaldrawsystemapi.exception.NotFoundException;
import br.com.futsal.futsaldrawsystemapi.model.Jogador;
import br.com.futsal.futsaldrawsystemapi.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final ModelMapper modelMapper;

    public Jogador cadastrarJogador(JogadorDTO dto) {
        validarJogadorExistente(dto);
        return jogadorRepository.save(modelMapper.map(dto, Jogador.class));
    }

    private void validarJogadorExistente(JogadorDTO dto) {
        if (Boolean.TRUE.equals(jogadorRepository.existsByNome(dto.getNome()))) {
            throw new ApiException("Jogador já cadastrado");
        }
    }

    public List<JogadorDTO> listarJogadores() {
        return jogadorRepository.findAll()
                .stream()
                .map(jogador -> modelMapper.map(jogador, JogadorDTO.class))
                .toList();
    }

    public void deletarJogador(Long id) {
        buscarJogador(id);
        jogadorRepository.deleteById(id);
    }

    private Jogador buscarJogador(Long id) {
        return jogadorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Jogador não encontrado"));

    }


    public List<List<JogadorDTO>> sortearTimes(SorteioRequestDTO request) {
        List<JogadorDTO> participantes = prepararParticipantes(request);
        Collections.shuffle(participantes);
        return distribuirEmTimes(participantes, request.getJogadoresPorTime());
    }

    private List<JogadorDTO> prepararParticipantes(SorteioRequestDTO request) {
        List<JogadorDTO> lista = new ArrayList<>();


        if (request.getIdsJogadoresFixos() != null) {
            request.getIdsJogadoresFixos().forEach(id -> {
                Jogador jogador = buscarJogador(id);
                lista.add(modelMapper.map(jogador, JogadorDTO.class));
            });
        }


        if (request.getNomesVisitantes() != null) {
            request.getNomesVisitantes().forEach(nome -> {
                JogadorDTO visitante = new JogadorDTO();
                visitante.setNome(nome + " (Visitante)");
                lista.add(visitante);
            });
        }

        return lista;
    }

    private List<List<JogadorDTO>> distribuirEmTimes(List<JogadorDTO> jogadores, int tamanho) {
        List<List<JogadorDTO>> times = new ArrayList<>();
        for (int i = 0; i < jogadores.size(); i += tamanho) {
            int fim = Math.min(i + tamanho, jogadores.size());
            times.add(new ArrayList<>(jogadores.subList(i, fim)));
        }
        return times;
    }

}
