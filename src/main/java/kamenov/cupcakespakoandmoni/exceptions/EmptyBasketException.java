package kamenov.cupcakespakoandmoni.exceptions;

public class EmptyBasketException extends RuntimeException {
    public EmptyBasketException() {
        super("The cart is empty");
    }
    public EmptyBasketException(String message) {
        super(message);
    }
    public EmptyBasketException(String message, Throwable cause) {
        super(message, cause);
    }
}
