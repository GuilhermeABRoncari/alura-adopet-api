package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.UserDTO;
import br.com.alura.adopet.adopet.domain.entity.UserRole;
import br.com.alura.adopet.adopet.domain.response.UserResponse;
import br.com.alura.adopet.adopet.rest.service.ShelterService;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<UserDTO> userDTOJson;
    @Autowired
    private JacksonTester<UserResponse> userResponseJson;
    @MockBean
    private TutorService tutorService;
    @MockBean
    private ShelterService shelterService;

    @Test
    @DisplayName("Deve retornar codigo 201, pois as informações obrigatorias são validas.")
    void sign_scene01() throws Exception {
        var userResponse = new UserResponse(null, "tutor", "tutor@email.com");
        when(tutorService.create(any())).thenReturn(userResponse);

        var response = mvc.perform(post("/sign")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJson.write(
                        new UserDTO(UserRole.TUTOR, "tutor@email.com", "37991090703", "tutor",
                                "123", "123", "animals tutor", "image", null)
                ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        var ResponseJson = userResponseJson.write(userResponse).getJson();
        assertThat(response.getContentAsString()).isEqualTo(ResponseJson);
    }
}