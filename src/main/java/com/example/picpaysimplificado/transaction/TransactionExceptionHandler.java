package com.example.picpaysimplificado.transaction;

import com.example.picpaysimplificado.exception.AnauthorizedTranscationException;
import com.example.picpaysimplificado.exception.InvalidTransactionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler {
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handleInvalidTransactionException(InvalidTransactionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AnauthorizedTranscationException.class)
    public ResponseEntity<Object> handleInvalidTransactionException(AnauthorizedTranscationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
