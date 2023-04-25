package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.response.PetResponse;
import br.com.alura.adopet.adopet.rest.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PetService petService;
    @GetMapping
    public Page<PetResponse> list(@PageableDefault(size = 9) Pageable pageable) {
        return petService.list(pageable);
    }
}
