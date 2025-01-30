package kamenov.cupcakespakoandmoni.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CakeNotFoundException extends RuntimeException {

    public CakeNotFoundException() {}

    public CakeNotFoundException(String message) {
        super(message);
    }
    public CakeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
