package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.TutorUpdateDTO;
import br.com.alura.adopet.adopet.rest.service.TutorService;
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
class TutorControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private TutorService tutorService;
    @Autowired
    private JacksonTester<TutorUpdateDTO> updateJson;

    @Test
    @DisplayName("Deve retornar codigo 200")
    @WithMockUser
    void listTest() throws Exception {
        var response = mvc.perform(get("/tutors")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 200")
    @WithMockUser
    void findByIdTeste() throws Exception {
        var response = mvc.perform(get("/tutors/3")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 200")
    @WithMockUser
    void putTutorTest() throws Exception {
        var response = mvc.perform(put("/tutors/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson.write(new TutorUpdateDTO("TutorTest", "about test")).getJson())
        ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deve retornar codigo 404")
    @WithMockUser
    void deleteTest() throws Exception {
        var response = mvc.perform(delete("/tutor/4")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}