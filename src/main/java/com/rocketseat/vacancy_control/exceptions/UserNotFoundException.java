package com.rocketseat.vacancy_control.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found");
    }
}
