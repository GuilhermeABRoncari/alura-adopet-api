package br.com.alura.adopet.adopet.domain.response;

import br.com.alura.adopet.adopet.domain.entity.Tutor;

public record TutorResponse(Long id, String name, String email) {
    public TutorResponse(Tutor tutor) {
        this(tutor.getId(), tutor.getName(), tutor.getEmail());
    }
}
