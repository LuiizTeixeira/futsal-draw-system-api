package br.com.futsal.futsaldrawsystemapi.controller;

import br.com.futsal.futsaldrawsystemapi.dto.JogadorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.futsal.futsaldrawsystemapi.service.JogadorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;
    private final ModelMapper modelMapper;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public JogadorDTO cadastrarJogador(@RequestBody JogadorDTO dto) {
        return modelMapper.map(jogadorService.cadastrarJogador(dto), JogadorDTO.class);
    }


}



