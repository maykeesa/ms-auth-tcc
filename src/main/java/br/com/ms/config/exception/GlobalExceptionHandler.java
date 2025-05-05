package br.com.ms.config.exception;

import br.com.ms.config.exception.exceptions.ServiceException;
import br.com.ms.utils.dto.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto.Body.ResponseError> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        var fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(
                new ResponseDto.Body.ResponseError(BAD_REQUEST.value(), ex.getClass().getSimpleName(), map));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto.Body.ResponseError> handlerEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(
                new ResponseDto.Body.ResponseError(NOT_FOUND.value(), ex.getClass().getSimpleName(), ex.getMessage()));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseDto.Body.ResponseError> handlerService(ServiceException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(
                new ResponseDto.Body.ResponseError(BAD_REQUEST.value(), ex.getClass().getSimpleName(), ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto.Body.ResponseError> handlerIllegalArgument(IllegalArgumentException ex) {
        if (ex.getMessage().contains("Invalid UUID string:")) {
            return ResponseEntity.status(BAD_REQUEST).body(
                    new ResponseDto.Body.ResponseError
                            (BAD_REQUEST.value(), ex.getClass().getSimpleName(), ex.getMessage()));
        }

        return null;
    }
}

