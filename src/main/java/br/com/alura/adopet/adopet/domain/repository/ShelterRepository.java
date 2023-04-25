package br.com.alura.adopet.adopet.domain.repository;

import br.com.alura.adopet.adopet.domain.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    boolean existsByEmail(String email);

    UserDetails findByEmail(String username);

    Shelter getReferenceByEmail(String subject);
}
