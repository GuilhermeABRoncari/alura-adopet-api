package br.com.alura.adopet.adopet.domain.response;

import br.com.alura.adopet.adopet.domain.entity.AdopetMessage;

public record AdopetMessageResponse(Long id, TutorResponse tutor, PetResponse pet, String message) {
    public AdopetMessageResponse(AdopetMessage message){
        this(message.getId(), new TutorResponse(message.getTutor()), new PetResponse(message.getPet()), message.getMessage());
    }
}
