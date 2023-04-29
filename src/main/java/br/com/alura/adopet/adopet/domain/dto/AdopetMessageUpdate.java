package br.com.alura.adopet.adopet.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdopetMessageUpdate(
        @NotNull
        @JsonAlias("pet_id")
        Long petId,
        @NotBlank
        String message) {
}
