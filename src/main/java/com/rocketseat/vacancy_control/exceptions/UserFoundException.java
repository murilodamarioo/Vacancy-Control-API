package com.rocketseat.vacancy_control.exceptions;

public class UserFoundException extends RuntimeException{
  public UserFoundException() {
    super("User already exists");
  }
}
