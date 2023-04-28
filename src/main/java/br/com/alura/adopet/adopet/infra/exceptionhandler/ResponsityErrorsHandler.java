package br.com.alura.adopet.adopet.infra.exceptionhandler;

import br.com.alura.adopet.adopet.domain.exception.DomainException;
import br.com.alura.adopet.adopet.domain.exception.DomainNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponsityErrorsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(DomainException.class)
    public ResponseEntity domainException(DomainException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest(MethodArgumentNotValidException ex) {
        var error = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(ExceptionValidation::new).toList());
    }

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity notFound(DomainNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity Error500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " +ex.getLocalizedMessage());
    }
    private record ExceptionValidation(String field, String message) {
        public ExceptionValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
