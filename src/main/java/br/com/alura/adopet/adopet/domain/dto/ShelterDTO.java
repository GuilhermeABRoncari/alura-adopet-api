package br.com.alura.adopet.adopet.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ShelterDTO(
        @NotBlank
        String name,
        @NotBlank
        String fone,
        @NotBlank
        @Email
        String email
) {
}
