package br.com.alura.adopet.adopet.domain.entity;

import br.com.alura.adopet.adopet.domain.dto.PetUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Pet")
@Table(name = "pets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne
    private Shelter shelter;
    private String name;
    @Column(name = "years_old")
    private String yearsOld;
    @Column(name = "animal_size")
    private String size;
    private String description;
    private boolean adopted;
    private String image;

    public void update(PetUpdateDTO petUpdateDTO) {
        if(petUpdateDTO.name() != null) this.name = petUpdateDTO.name();
        if(petUpdateDTO.yearsOld() != null) this.yearsOld = petUpdateDTO.yearsOld();
        if(petUpdateDTO.size() != null) this.size = petUpdateDTO.size();
        if(petUpdateDTO.description() != null) this.description = petUpdateDTO.description();
        if(petUpdateDTO.image() != null) this.image = petUpdateDTO.image();
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public void setAdopted() {
        if(this.adopted) this.adopted = false;
        if(!this.adopted) this.adopted = true;
    }
}
