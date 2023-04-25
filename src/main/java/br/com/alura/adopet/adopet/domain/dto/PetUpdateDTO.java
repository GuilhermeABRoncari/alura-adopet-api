package br.com.alura.adopet.adopet.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record PetUpdateDTO(
        @NotNull
        @JsonAlias("pet_id")
        Long id,
        @JsonAlias("shelter_id")
        Long shelterId,
        String name,
        @JsonAlias("years_old")
        String yearsOld,
        String size,
        String description,
        String image) {
}
