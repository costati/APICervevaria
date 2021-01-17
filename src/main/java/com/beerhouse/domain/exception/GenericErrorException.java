package com.beerhouse.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class GenericErrorException extends RuntimeException {
    public GenericErrorException(String mensagem) {
        super(mensagem);
    }
}
