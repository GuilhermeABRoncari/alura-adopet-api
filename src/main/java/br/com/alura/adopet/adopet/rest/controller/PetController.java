package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.AdoptPetDTO;
import br.com.alura.adopet.adopet.domain.dto.PetDTO;
import br.com.alura.adopet.adopet.domain.dto.PetUpdateDTO;
import br.com.alura.adopet.adopet.domain.response.AdoptPetResponse;
import br.com.alura.adopet.adopet.domain.response.PetResponse;
import br.com.alura.adopet.adopet.rest.service.PetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
@EnableMethodSecurity(securedEnabled = true)
@Secured("ROLE_ADMIN")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PetController {
    private PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse create(@RequestBody @Valid PetDTO petDTO) {
        return petService.create(petDTO);
    }

    @GetMapping
    public Page<PetResponse> list(@PageableDefault(size = 9) Pageable pageable) {
        return petService.list(pageable);
    }

    @GetMapping("{id}")
    public PetResponse find(@PathVariable Long id) {
        return petService.find(id);
    }

    @PutMapping
    public PetResponse update(@RequestBody PetUpdateDTO petUpdateDTO) {
        return petService.update(petUpdateDTO);
    }

    @PatchMapping
    public AdoptPetResponse adoptPet(@RequestBody AdoptPetDTO adoptPetDTO) {
        return petService.adoptPet(adoptPetDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        petService.delete(id);
    }
}
