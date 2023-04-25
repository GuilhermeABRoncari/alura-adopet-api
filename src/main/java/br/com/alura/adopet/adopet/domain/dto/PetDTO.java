package br.com.alura.adopet.adopet.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetDTO(
        @NotNull
        @JsonAlias("shelter_id")
        Long shelterId,
        @NotBlank
        String name,
        @JsonAlias("years_old")
        @NotBlank
        String yearsOld,
        @NotBlank
        String size,
        @NotBlank
        String description,
        @NotBlank
        String image
) {
}
