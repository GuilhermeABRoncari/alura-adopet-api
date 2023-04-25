package br.com.alura.adopet.adopet.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.core.annotation.AliasFor;

public record TutorDTO(
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password,
        @JsonAlias("confirmation_password")
        @NotBlank
        String confirmationPassword,
        String about) {
}
