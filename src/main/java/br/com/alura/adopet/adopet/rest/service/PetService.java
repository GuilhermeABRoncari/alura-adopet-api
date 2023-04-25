package br.com.alura.adopet.adopet.rest.service;

import br.com.alura.adopet.adopet.domain.dto.AdoptPetDTO;
import br.com.alura.adopet.adopet.domain.dto.PetDTO;
import br.com.alura.adopet.adopet.domain.dto.PetUpdateDTO;
import br.com.alura.adopet.adopet.domain.entity.Pet;
import br.com.alura.adopet.adopet.domain.exception.DomainNotFoundException;
import br.com.alura.adopet.adopet.domain.repository.PetRepository;
import br.com.alura.adopet.adopet.domain.repository.ShelterRepository;
import br.com.alura.adopet.adopet.domain.repository.TutorRepository;
import br.com.alura.adopet.adopet.domain.response.AdoptPetResponse;
import br.com.alura.adopet.adopet.domain.response.PetResponse;
import br.com.alura.adopet.adopet.domain.response.ShelterResponse;
import br.com.alura.adopet.adopet.domain.response.TutorResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {
    private PetRepository petRepository;
    private ShelterRepository shelterRepository;
    private final TutorRepository tutorRepository;

    @Transactional
    public PetResponse create(PetDTO petDTO) {
        var shelter = shelterRepository.findById(petDTO.shelterId()).orElseThrow(() -> new DomainNotFoundException());
        var pet = new Pet(null, shelter, petDTO.name(), petDTO.yearsOld(), petDTO.size(), petDTO.description(), false, petDTO.image());
        shelter.getPetList().add(pet);
        petRepository.save(pet);
        return new PetResponse(pet);
    }

    public Page<PetResponse> list(Pageable pageable) {
        return petRepository.findAll(pageable).map(PetResponse::new);
    }

    public PetResponse find(Long id) {
        return new PetResponse(petRepository.findById(id).orElseThrow(() -> new DomainNotFoundException()));
    }

    @Transactional
    public PetResponse update(PetUpdateDTO petUpdateDTO) {
        var pet = petRepository.findById(petUpdateDTO.id()).orElseThrow(() -> new DomainNotFoundException());

        if (petUpdateDTO.shelterId() != null) {
            var newShelter = shelterRepository.findById(petUpdateDTO.shelterId()).orElseThrow(() -> new DomainNotFoundException());

            if (pet.getShelter() != null) {
                var oldShelter = pet.getShelter();
                oldShelter.getPetList().remove(pet);
            }

            if (!newShelter.getPetList().contains(pet)) {
                pet.setShelter(newShelter);
                newShelter.getPetList().add(pet);
            }
        }

        pet.update(petUpdateDTO);
        return new PetResponse(pet);
    }

    @Transactional
    public void delete(Long id) {
        if (!petRepository.existsById(id)) throw new DomainNotFoundException();
        petRepository.deleteById(id);
    }

    @Transactional
    public AdoptPetResponse adoptPet(AdoptPetDTO adoptPetDTO) {
        var pet = petRepository.findById(adoptPetDTO.petId()).orElseThrow(() -> new DomainNotFoundException());
        var tutor = tutorRepository.findById(adoptPetDTO.tutorId()).orElseThrow(() -> new DomainNotFoundException());
        var shelter = shelterRepository.findById(adoptPetDTO.shelterId()).orElseThrow(() -> new DomainNotFoundException());
        pet.setAdopted();
        shelter.getPetList().remove(pet);
        return new AdoptPetResponse(new TutorResponse(tutor), new PetResponse(pet), new ShelterResponse(shelter));
    }
}
