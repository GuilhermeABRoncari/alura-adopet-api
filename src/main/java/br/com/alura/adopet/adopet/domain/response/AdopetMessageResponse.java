package br.com.alura.adopet.adopet.domain.response;

import br.com.alura.adopet.adopet.domain.entity.AdopetMessage;

import java.time.OffsetDateTime;

public record AdopetMessageResponse(Long id, TutorResponse tutor, PetResponse pet, String message,
                                    OffsetDateTime date) {
    public AdopetMessageResponse(AdopetMessage message) {
        this(message.getId(), new TutorResponse(message.getTutor()), new PetResponse(message.getPet()), message.getMessage(), message.getDateTime());
    }
}
