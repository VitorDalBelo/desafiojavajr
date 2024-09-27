package com.vitor.crudjava.exceptions.httpExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public InternalServerErrorRequestException(String ex){
        super(ex);
    }
}
