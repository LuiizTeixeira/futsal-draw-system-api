package br.com.futsal.futsaldrawsystemapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JogadorDTO {
    private Long id;
    private String nome;
    private Integer habilidade;
    private Integer numero;
    private String presenca;
}

