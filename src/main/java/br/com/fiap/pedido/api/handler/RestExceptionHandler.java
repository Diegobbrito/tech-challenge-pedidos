package br.com.fiap.pedido.api.handler;

import br.com.fiap.pedido.core.exception.*;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handlerPedidoInexistenteException(PedidoInexistenteException ex){
        final var details = new ExceptionDetails(ex.getMessage());
        return new ResponseEntity(details, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handlerPedidoStatusException(PedidoStatusException ex){
        final var details = new ExceptionDetails(ex.getMessage());
        return ResponseEntity.badRequest().body(details);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handlerException(Exception ex){
        final var details = new ExceptionDetails(ex.getMessage());
        return new ResponseEntity(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@Getter
class ExceptionDetails {
    private String error;
    private LocalDateTime timestamp;

    public ExceptionDetails( String details) {
        this.error = details;
        this.timestamp = LocalDateTime.now();
    }
}