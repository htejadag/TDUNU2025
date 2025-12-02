package unu.td.MsAcademico.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyDeactivateException extends RuntimeException {
    public AlreadyDeactivateException(String message) {
        super(message);
    }
}