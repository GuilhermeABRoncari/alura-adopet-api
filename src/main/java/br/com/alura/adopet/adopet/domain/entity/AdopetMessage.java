package br.com.alura.adopet.adopet.domain.entity;

import br.com.alura.adopet.adopet.domain.dto.AdopetMessageUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "AdopetMessage")
@Table(name = "adopet_messages")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdopetMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Tutor tutor;
    @ManyToOne
    private Shelter shelter;
    @OneToOne
    private Pet pet;
    private String message;

    public void update(AdopetMessageUpdate adopetMessageUpdate, Pet pet) {
        if(adopetMessageUpdate.petId() != null) this.pet = pet;
        if(adopetMessageUpdate.message() != null) this.message = adopetMessageUpdate.message();
    }
}
