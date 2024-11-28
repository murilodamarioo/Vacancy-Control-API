package com.rocketseat.vacancy_control.exceptions;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("Job not found");
    }
}
