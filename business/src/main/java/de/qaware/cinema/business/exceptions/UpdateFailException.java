package de.qaware.cinema.business.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UpdateFailException extends RuntimeException {


    public UpdateFailException(String message) {
        super(message);
    }
}
