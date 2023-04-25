package br.com.alura.adopet.adopet.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record AdoptPetDTO(
        @NotNull
        @JsonAlias("tutor_id")
        Long tutorId,
        @NotNull
        @JsonAlias("shelter_id")
        Long shelterId,
        @NotNull
        @JsonAlias("pet_id")
        Long petId) {
}
