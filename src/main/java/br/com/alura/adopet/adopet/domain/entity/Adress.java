package br.com.alura.adopet.adopet.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    String cep;
    String state;
    String city;
    String neighborhood;
    String street;
    String number;
}
