package br.com.alura.adopet.adopet.domain.entity;

import br.com.alura.adopet.adopet.domain.dto.ShelterUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Shelter")
@Table(name = "shelters")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Shelter implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Pet> petList = new ArrayList<>();
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<AdopetMessage> messageList = new ArrayList<>();
    private String name;
    private String fone;
    private String email;
    private String password;
    @Embedded
    private Adress adress;

    public void update(ShelterUpdateDTO shelterUpdateDTO) {
        if (shelterUpdateDTO.name() != null) this.name = shelterUpdateDTO.name();
        if (shelterUpdateDTO.fone() != null) this.fone = shelterUpdateDTO.fone();
        if (shelterUpdateDTO.email() != null) this.email = shelterUpdateDTO.email();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
