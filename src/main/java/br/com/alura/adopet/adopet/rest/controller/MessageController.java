package br.com.alura.adopet.adopet.rest.controller;

import br.com.alura.adopet.adopet.domain.dto.AdopetMessageDTO;
import br.com.alura.adopet.adopet.domain.entity.Tutor;
import br.com.alura.adopet.adopet.domain.response.AdopetMessageResponse;
import br.com.alura.adopet.adopet.infra.security.AuthenticationFacade;
import br.com.alura.adopet.adopet.rest.service.AdopetMessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor
public class MessageController {

    private AdopetMessageService adopetMessageService;
    private AuthenticationFacade authenticationFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_USER")
    public AdopetMessageResponse postMessage(@RequestBody @Valid AdopetMessageDTO messageDTO) {
        String email = authenticationFacade.getAuthentication().getName();
        return adopetMessageService.postMessage(email, messageDTO);
    }
    @GetMapping
    public Page<AdopetMessageResponse> listAllMessages(Pageable pageable) {
        String email = authenticationFacade.getAuthentication().getName();
        return adopetMessageService.list(email, pageable);
    }
}
