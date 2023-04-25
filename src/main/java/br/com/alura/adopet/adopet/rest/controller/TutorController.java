package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.TutorUpdateDTO;
import br.com.alura.adopet.adopet.domain.response.TutorResponse;
import br.com.alura.adopet.adopet.rest.service.TutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tutors")
@AllArgsConstructor
public class TutorController {
    private TutorService tutorService;

    @GetMapping
    public List<TutorResponse> list() {
        return tutorService.list();
    }

    @GetMapping("{id}")
    public TutorResponse find(@PathVariable Long id) {
        return tutorService.find(id);
    }

    @PutMapping("{id}")
    public TutorResponse put(@PathVariable Long id, @RequestBody TutorUpdateDTO tutorUpdateDTO) {
        return tutorService.put(id, tutorUpdateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tutorService.delete(id);
    }
}
