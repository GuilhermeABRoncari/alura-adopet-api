package br.com.alura.adopet.adopet.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdopetMessageDTO(
        @NotNull
        @JsonAlias("shelter_id")
        Long shelterId,
        @NotBlank
        @JsonAlias("pet_name")
        String petName,
        @NotBlank
        String message) {
}
