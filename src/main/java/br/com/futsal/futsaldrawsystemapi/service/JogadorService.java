package br.com.futsal.futsaldrawsystemapi.service;

import br.com.futsal.futsaldrawsystemapi.dto.JogadorDTO;
import br.com.futsal.futsaldrawsystemapi.model.Jogador;
import br.com.futsal.futsaldrawsystemapi.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import br.com.futsal.futsaldrawsystemapi.exception.ApiException;

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
        if (jogadorRepository.existsByNome(dto.getNome())) {
            throw new ApiException("Jogador já cadastrado");
        }
    }
}

