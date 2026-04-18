package br.com.futsal.futsaldrawsystemapi.controller;

import br.com.futsal.futsaldrawsystemapi.dto.JogadorDTO;
import br.com.futsal.futsaldrawsystemapi.dto.SorteioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import br.com.futsal.futsaldrawsystemapi.service.JogadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/jogador")
public class JogadorController {

    private final JogadorService jogadorService;
    private final ModelMapper modelMapper;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public JogadorDTO cadastrarJogador(@RequestBody JogadorDTO dto) {
        return modelMapper.map(jogadorService.cadastrarJogador(dto), JogadorDTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<JogadorDTO> listarJogadores() {
        return jogadorService.listarJogadores();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarJogador(@PathVariable Long id) {
        jogadorService.deletarJogador(id);
    }

    @PostMapping("/sortear")
    @ResponseStatus(HttpStatus.OK)
    public List<List<JogadorDTO>> sortear(@RequestBody SorteioRequestDTO request) {
        return jogadorService.sortearTimes(request);
    }


}



