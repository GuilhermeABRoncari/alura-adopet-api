package br.com.alura.adopet.adopet.domain.repository;

import br.com.alura.adopet.adopet.domain.entity.AdopetMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopetMessageRepository extends JpaRepository<AdopetMessage, Long> {
}
