package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.AuthenticationDTO;
import br.com.alura.adopet.adopet.domain.dto.UserDTO;
import br.com.alura.adopet.adopet.domain.entity.Shelter;
import br.com.alura.adopet.adopet.domain.entity.Tutor;
import br.com.alura.adopet.adopet.domain.entity.UserRole;
import br.com.alura.adopet.adopet.domain.exception.DomainException;
import br.com.alura.adopet.adopet.domain.response.AuthResponse;
import br.com.alura.adopet.adopet.domain.response.UserResponse;
import br.com.alura.adopet.adopet.infra.security.TokenService;
import br.com.alura.adopet.adopet.rest.service.ShelterService;
import br.com.alura.adopet.adopet.rest.service.TutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private TutorService tutorService;
    private ShelterService shelterService;
    private static final String INVALID_PASSWORD = "The entered passwords are not the same.";

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        if (tutorService.exists(authenticationDTO.email())) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var tutor = (Tutor) authentication.getPrincipal();
            var tokenJWT = tokenService.generateToken(tutor);

            return new AuthResponse(tutor, tokenJWT);
        } else if (shelterService.exists(authenticationDTO.email())) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var shelter = (Shelter) authentication.getPrincipal();
            var tokenJWT = tokenService.generateToken(shelter);

            return new AuthResponse(shelter, tokenJWT);
        } else throw new DomainException("Invalid email.");
    }

    @PostMapping("sign")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse sign(@RequestBody @Valid UserDTO userDTO) {
        if (!userDTO.password().equals(userDTO.confirmationPassword())) throw new DomainException(INVALID_PASSWORD);
        if (userDTO.userRole().equals(UserRole.TUTOR)) return tutorService.create(userDTO);
        if (userDTO.userRole().equals(UserRole.SHELTER)) return shelterService.create(userDTO);
        throw new DomainException("Erro");
    }
}


