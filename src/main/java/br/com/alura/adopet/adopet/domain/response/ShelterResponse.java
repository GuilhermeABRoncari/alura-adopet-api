package br.com.alura.adopet.adopet.domain.response;

import br.com.alura.adopet.adopet.domain.entity.Shelter;

public record ShelterResponse(Long id, String name, String fone, String email) {
    public ShelterResponse(Shelter shelter) {
        this(shelter.getId(), shelter.getName(), shelter.getFone(), shelter.getEmail());
    }
}
