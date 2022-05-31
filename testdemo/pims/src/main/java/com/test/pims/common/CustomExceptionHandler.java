package com.test.pims.common;

import com.test.pims.common.exceptions.IllegalIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Общий обработчик ошибок при работе REST контроллеров
 * <p>
 * Created by SafinaAA on 28.05.2022
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalIdException.class)
    public ResponseEntity handleException(IllegalIdException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
