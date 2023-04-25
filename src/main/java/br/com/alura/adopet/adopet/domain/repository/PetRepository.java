package br.com.alura.adopet.adopet.domain.repository;

import br.com.alura.adopet.adopet.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
