package br.com.alura.adopet.adopet.rest.service;

import br.com.alura.adopet.adopet.domain.dto.AdopetMessageDTO;
import br.com.alura.adopet.adopet.domain.entity.AdopetMessage;
import br.com.alura.adopet.adopet.domain.entity.Shelter;
import br.com.alura.adopet.adopet.domain.entity.Tutor;
import br.com.alura.adopet.adopet.domain.repository.AdopetMessageRepository;
import br.com.alura.adopet.adopet.domain.repository.PetRepository;
import br.com.alura.adopet.adopet.domain.repository.ShelterRepository;
import br.com.alura.adopet.adopet.domain.repository.TutorRepository;
import br.com.alura.adopet.adopet.domain.response.AdopetMessageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdopetMessageService {

    private AdopetMessageRepository adopetMessageRepository;
    private TutorRepository tutorRepository;
    private ShelterRepository shelterRepository;
    private final PetRepository petRepository;

    @Transactional
    public AdopetMessageResponse postMessage(String email, AdopetMessageDTO messageDTO) {
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        var shelter = shelterRepository.findById(messageDTO.shelterId()).orElseThrow(() -> new EntityNotFoundException("Shelter not found."));
        var pet = petRepository.findByNameLike("%" + messageDTO.petName() + "%");
        if (pet == null) throw new EntityNotFoundException("Pet whit this name not found.");
        if (shelter.getPetList().contains(pet)) {
            var message = new AdopetMessage(null, tutor, shelter, pet, messageDTO.message());
            adopetMessageRepository.save(message);
            tutor.getMessageList().add(message);
            shelter.getMessageList().add(message);
            return new AdopetMessageResponse(message);
        }
        throw new EntityNotFoundException("This shelter not cointains this pet.");
    }

    public Page<AdopetMessageResponse> list(String email, Pageable pageable) {
        Tutor tutor = (Tutor) tutorRepository.findByEmail(email);
        Shelter shelter = (Shelter) shelterRepository.findByEmail(email);
        List<AdopetMessageResponse> list = new ArrayList<>();

        if (tutor != null) {
            tutor.getMessageList().forEach(adopetMessage -> list.add(new AdopetMessageResponse(adopetMessage)));
        }
        if (shelter != null) {
            shelter.getMessageList().forEach(adopetMessage -> list.add(new AdopetMessageResponse(adopetMessage)));
        }
        Page<AdopetMessageResponse> page = new PageImpl<>(list, pageable, list.size());
        return page;
    }
}
