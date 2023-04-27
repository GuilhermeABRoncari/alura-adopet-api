package br.com.alura.adopet.adopet.domain.dto;

import br.com.alura.adopet.adopet.domain.entity.Adress;
import br.com.alura.adopet.adopet.domain.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull(message = "This field can not be null, use TUTOR or SHELTER.")
        UserRole userRole,
        @NotBlank
        @Email
        String email,
        String phone,
        @NotBlank
        String userName,
        @NotBlank
        String password,
        String confirmationPassword,
        String about,
        String image,
        Adress adress
) {
}
