package br.com.alura.adopet.adopet.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record AdoptPetDTO(
        @NotNull(message = "Can not be null.")
        @JsonAlias("tutor_id")
        Long tutorId,
        @NotNull(message = "Can not be null.")
        @JsonAlias("shelter_id")
        Long shelterId,
        @NotNull(message = "Can not be null.")
        @JsonAlias("pet_id")
        Long petId) {
}
