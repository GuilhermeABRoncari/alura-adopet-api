package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.ShelterUpdateDTO;
import br.com.alura.adopet.adopet.domain.response.ShelterResponse;
import br.com.alura.adopet.adopet.rest.service.ShelterService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelters")
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
@SecurityRequirement(name = "bearer-key")
public class ShelterController {
    private ShelterService shelterService;


    @GetMapping
    public List<ShelterResponse> list() {
        return shelterService.list();
    }

    @GetMapping("{id}")
    public ShelterResponse find(@PathVariable Long id) {
        return shelterService.find(id);
    }

    @PutMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ShelterResponse put(@PathVariable Long id, @RequestBody ShelterUpdateDTO shelterUpdateDTO) {
        return shelterService.update(id, shelterUpdateDTO);
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable Long id) {
        shelterService.delete(id);
    }
}
