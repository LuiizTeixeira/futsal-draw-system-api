package br.com.futsal.futsaldrawsystemapi.dto;

import lombok.Builder;

@Builder
public record ResponseErrorDTO(String error) {

}
