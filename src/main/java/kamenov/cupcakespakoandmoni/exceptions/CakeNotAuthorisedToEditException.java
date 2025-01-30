package kamenov.cupcakespakoandmoni.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.LOCKED)
public class CakeNotAuthorisedToEditException extends RuntimeException {
    public CakeNotAuthorisedToEditException() {}
    public CakeNotAuthorisedToEditException(String message) {
        super(message);
    }
    public CakeNotAuthorisedToEditException(String message, Throwable cause) {
        super(message,cause);
    }
}
