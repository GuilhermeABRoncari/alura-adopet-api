package br.com.alura.adopet.adopet.rest.service;

import br.com.alura.adopet.adopet.domain.dto.ShelterUpdateDTO;
import br.com.alura.adopet.adopet.domain.dto.UserDTO;
import br.com.alura.adopet.adopet.domain.entity.Adress;
import br.com.alura.adopet.adopet.domain.entity.Shelter;
import br.com.alura.adopet.adopet.domain.exception.DomainException;
import br.com.alura.adopet.adopet.domain.exception.DomainNotFoundException;
import br.com.alura.adopet.adopet.domain.repository.ShelterRepository;
import br.com.alura.adopet.adopet.domain.response.ShelterResponse;
import br.com.alura.adopet.adopet.domain.response.UserResponse;
import br.com.alura.adopet.adopet.infra.security.SecurityConfigurations;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShelterService {
    private ShelterRepository shelterRepository;
    private SecurityConfigurations securityConfigurations;
    private static final String IN_USE = "Email is already in use.";
    private static final String INVALID_FIELD = "Fields: 'phone', 'cep', 'state', 'city', 'neighborhood', 'street' and 'number' is mandatory.";

    @Transactional
    public UserResponse create(UserDTO userDTO) {
        if (shelterRepository.existsByEmail(userDTO.email())) throw new DomainException(IN_USE);
        if (
                userDTO.phone() == null || userDTO.phone().isBlank() ||
                userDTO.adress().getCep() == null || userDTO.adress().getCep().isBlank() ||
                userDTO.adress().getState() == null || userDTO.adress().getState().isBlank() ||
                userDTO.adress().getCity() == null || userDTO.adress().getCity().isBlank() ||
                userDTO.adress().getNeighborhood() == null ||userDTO.adress().getNeighborhood().isBlank() ||
                userDTO.adress().getStreet() == null || userDTO.adress().getStreet().isBlank() ||
                userDTO.adress().getNumber() == null || userDTO.adress().getNumber().isBlank())
            throw new DomainException(INVALID_FIELD);
        var shelter = new Shelter(null, null, userDTO.userName(),
                userDTO.phone(), userDTO.email(), securityConfigurations.passwordEncoder().encode(userDTO.password()),
                new Adress(
                        userDTO.adress().getCep(), userDTO.adress().getState(), userDTO.adress().getCity(),
                        userDTO.adress().getNeighborhood(), userDTO.adress().getStreet(), userDTO.adress().getNumber()));
        shelterRepository.save(shelter);
        return new UserResponse(shelter);
    }

    public List<ShelterResponse> list() {
        var shelters = shelterRepository.findAll();
        List<ShelterResponse> list = new ArrayList<>();
        shelters.forEach(shelter -> list.add(new ShelterResponse(shelter)));
        return list;
    }

    public ShelterResponse find(Long id) {
        return new ShelterResponse(shelterRepository.findById(id).orElseThrow(() -> new DomainNotFoundException()));
    }

    @Transactional
    public ShelterResponse update(Long id, ShelterUpdateDTO shelterUpdateDTO) {
        var shelter = shelterRepository.findById(id).orElseThrow(() -> new DomainNotFoundException());
        shelter.update(shelterUpdateDTO);
        return new ShelterResponse(shelter);
    }

    @Transactional
    public void delete(Long id) {
        if (!shelterRepository.existsById(id)) throw new DomainNotFoundException();
        if (shelterRepository.getReferenceById(id).getPetList().isEmpty()) {
            shelterRepository.deleteById(id);
        } else throw new DomainException("This shelter contains pets, and can not be deleted!");
    }

    public boolean exists(String email) {
        return shelterRepository.existsByEmail(email);
    }
}
