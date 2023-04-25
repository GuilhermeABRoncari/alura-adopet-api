package br.com.alura.adopet.adopet.domain.response;

import br.com.alura.adopet.adopet.domain.entity.Shelter;
import br.com.alura.adopet.adopet.domain.entity.Tutor;

public record UserResponse(Long id, String user, String email) {
    public UserResponse(Tutor tutor) {
        this(tutor.getId(), tutor.getName(), tutor.getEmail());
    }
    public UserResponse(Shelter shelter) {
        this(shelter.getId(), shelter.getName(), shelter.getEmail());
    }
}
