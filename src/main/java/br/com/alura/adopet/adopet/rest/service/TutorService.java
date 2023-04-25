package br.com.alura.adopet.adopet.rest.service;

import br.com.alura.adopet.adopet.domain.dto.TutorUpdateDTO;
import br.com.alura.adopet.adopet.domain.dto.UserDTO;
import br.com.alura.adopet.adopet.domain.entity.Tutor;
import br.com.alura.adopet.adopet.domain.exception.DomainException;
import br.com.alura.adopet.adopet.domain.exception.DomainNotFoundException;
import br.com.alura.adopet.adopet.domain.repository.TutorRepository;
import br.com.alura.adopet.adopet.domain.response.TutorResponse;
import br.com.alura.adopet.adopet.domain.response.UserResponse;
import br.com.alura.adopet.adopet.infra.security.SecurityConfigurations;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TutorService {
    private TutorRepository tutorRepository;
    private SecurityConfigurations securityConfigurations;
    private static final String IN_USE = "Email is already in use.";

    @Transactional
    public UserResponse create(UserDTO userDTO) {
        if (tutorRepository.existsByEmail(userDTO.email())) throw new DomainException(IN_USE);
        var tutor = new Tutor(null, userDTO.userName(), userDTO.email(),
                securityConfigurations.passwordEncoder().encode(userDTO.password()), userDTO.about(), userDTO.image());
        tutorRepository.save(tutor);
        return new UserResponse(tutor);
    }

    public List<TutorResponse> list() {
        List<Tutor> all = tutorRepository.findAll();
        List<TutorResponse> list = new ArrayList<>();
        all.forEach(tutor -> list.add(new TutorResponse(tutor)));

        return list;
    }

    public TutorResponse find(Long id) {
        return new TutorResponse(tutorRepository.findById(id).orElseThrow(() -> new DomainNotFoundException()));
    }

    @Transactional
    public TutorResponse put(Long id, TutorUpdateDTO tutorUpdateDTO) {
        var tutor = tutorRepository.findById(id).orElseThrow(() -> new DomainNotFoundException());
        tutor.update(tutorUpdateDTO);
        return new TutorResponse(tutor);
    }

    @Transactional
    public void delete(Long id) {
        if (!tutorRepository.existsById(id)) throw new DomainNotFoundException();
        tutorRepository.deleteById(id);
    }

    public boolean exists(String email) {
        return tutorRepository.existsByEmail(email);
    }
}

