package br.com.alura.adopet.adopet.domain.repository;

import br.com.alura.adopet.adopet.domain.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByEmail(String email);

    UserDetails findByEmail(String username);
}
