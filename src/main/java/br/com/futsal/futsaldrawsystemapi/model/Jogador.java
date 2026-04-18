package br.com.futsal.futsaldrawsystemapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "JOGADOR", schema = "FUTSAL")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_jogador")
    @SequenceGenerator(name = "sq_jogador", sequenceName = "FUTSAL.SQ_JOGADOR", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "HABILIDADE")
    private Integer habilidade;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "PRESENCA")
    private String presenca;

}
