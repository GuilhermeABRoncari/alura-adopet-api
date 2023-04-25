package br.com.alura.adopet.adopet.domain.response;

import br.com.alura.adopet.adopet.domain.entity.Pet;

public record PetResponse(Long id, String name,
                          String yearsOld, String size, String description, boolean adopted, String image,  ShelterResponse shelter) {
    public PetResponse(Pet pet) {
        this(pet.getId(), pet.getName(), pet.getYearsOld(),
                pet.getSize(), pet.getDescription(), pet.isAdopted(), pet.getImage(),  new ShelterResponse(pet.getShelter()));
    }
}
