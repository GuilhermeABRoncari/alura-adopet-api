package br.com.alura.adopet.adopet.domain.repository;

import br.com.alura.adopet.adopet.domain.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findAllByAdoptedFalse(Pageable pageable);

    Pet findByNameLike(String name);
}
