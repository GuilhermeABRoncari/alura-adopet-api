package br.com.alura.adopet.adopet.domain.response;

import br.com.alura.adopet.adopet.domain.entity.Shelter;
import br.com.alura.adopet.adopet.domain.entity.Tutor;

public record AuthResponse(Long id, String name, String token) {
    public AuthResponse(Tutor tutor, String tutorJWT) {
        this(tutor.getId(), tutor.getName(), tutorJWT);
    }
    public AuthResponse(Shelter shelter, String tutorJWT) {
        this(shelter.getId(), shelter.getName(), tutorJWT);
    }
}
