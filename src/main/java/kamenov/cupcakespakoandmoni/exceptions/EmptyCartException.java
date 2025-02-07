package kamenov.cupcakespakoandmoni.exceptions;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("The cart is empty");
    }
    public EmptyCartException(String message) {
        super(message);
    }
    public EmptyCartException(String message, Throwable cause) {
        super(message, cause);
    }
}
