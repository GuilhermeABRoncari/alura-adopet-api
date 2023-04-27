package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.AdoptPetDTO;
import br.com.alura.adopet.adopet.domain.dto.PetDTO;
import br.com.alura.adopet.adopet.domain.dto.PetUpdateDTO;
import br.com.alura.adopet.adopet.rest.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PetControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<PetDTO> petDTOJson;
    @Autowired
    private JacksonTester<PetUpdateDTO> updateDTOJson;
    @Autowired
    private JacksonTester<AdoptPetDTO> adopetPetDTOJson;
    @MockBean
    private PetService petService;

    @Test
    @DisplayName("Deve retornar codigo 201")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void createTest() throws Exception {
        var response = mvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(petDTOJson.write(new PetDTO(1L, "pet", "1 month", "little", "calm", "image")).getJson())
                ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
    @Test
    @DisplayName("Deve retornar codigo 200")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void listTest() throws Exception {
        var response = mvc.perform(get("/pets")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    @DisplayName("Deve retornar codigo 200")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void findByIdTest() throws Exception {
        var response = mvc.perform(get("/pets/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    @DisplayName("Deve retornar codigo 200")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void updateTest() throws Exception {
        var response = mvc.perform(put("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateDTOJson.write(
                        new PetUpdateDTO(1L, null, "petTest", "age test", "size test", "description test", "image test")).getJson())
        ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    @DisplayName("Deve retornar codigo 200")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void pacthTest() throws Exception {
        var response = mvc.perform(patch("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(adopetPetDTOJson.write(new AdoptPetDTO(1L, 1L, 1L)).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    @DisplayName("Deve retornar codigo 204")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deleteTest() throws Exception {
        var response = mvc.perform(delete("/pets/4")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}