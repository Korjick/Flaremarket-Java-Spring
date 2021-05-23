package ru.itis.flaremarket.handlers;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.flaremarket.exception.NotValidParameterException;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.BindException;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = {EntityExistsException.class})
    public void entityExistsExceptionHandler(EntityExistsException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = {NotValidParameterException.class})
    public void methodArgumentNotValidExceptionHandler(NotValidParameterException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
