package com.sergioruy.sergiofoodapi.api.exceptionHandler;

import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(
            EntityNotFoundException e) {
        Problem problem = Problem.builder()
                .dataTime(LocalDateTime.now())
                .message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(
            BusinessException e) {
        Problem problem = Problem.builder()
                .dataTime(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException() {
        Problem problem = Problem.builder()
                .dataTime(LocalDateTime.now())
                .message("This kind of media type is not accept.").build();
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(problem);
    }

    @ExceptionHandler(EntityUsedException.class)
    public ResponseEntity<?> handlerEntityUsedException(
            EntityNotFoundException e) {
        Problem problem = Problem.builder()
                .dataTime(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problem);
    }
}
