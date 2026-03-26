package br.com.futsal.futsaldrawsystemapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SorteioRequestDTO {
    private List<Long> idsJogadoresFixos;
    private List<String> jogadorVisitante;
    private int jogadoresPorTime;
}
