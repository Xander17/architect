package ru.geekbrains.atmclient.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.atmclient.exception.AdminNotAuthorizedException;
import ru.geekbrains.atmclient.exception.UserNotAuthorizedException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotAuthorizedException.class)
    public String userNotAuth() {
        return "redirect:/user/auth";
    }

    @ExceptionHandler(AdminNotAuthorizedException.class)
    public String adminNotAuth() {
        return "redirect:/admin/auth";
    }
}
