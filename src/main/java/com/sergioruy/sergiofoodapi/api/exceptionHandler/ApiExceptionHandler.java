package com.sergioruy.sergiofoodapi.api.exceptionHandler;

import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(EntityUsedException.class)
    public ResponseEntity<?> handlerEntityUsedException(
            EntityNotFoundException e) {
        Problem problem = Problem.builder()
                .dataTime(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problem);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        body = Problem.builder()
                .dataTime(LocalDateTime.now())
                .message(e.getMessage()).build();

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
