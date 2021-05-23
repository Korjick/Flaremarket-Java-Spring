package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.flaremarket.exception.NotValidParameterException;
import ru.itis.flaremarket.models.forms.user.UserForm;
import ru.itis.flaremarket.service.SignUpService;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.lang.reflect.Parameter;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public String signUp(@Valid UserForm form, BindingResult result) throws BindException {
        if(result.hasErrors()){
            throw new NotValidParameterException(result.getAllErrors().stream().findFirst().get().getDefaultMessage());
        }
        signUpService.signUp(form);
        return "redirect:/?acceptYourAccount";
    }
}
