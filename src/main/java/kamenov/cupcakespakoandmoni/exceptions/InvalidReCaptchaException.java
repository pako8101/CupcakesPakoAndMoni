package kamenov.cupcakespakoandmoni.exceptions;

public class InvalidReCaptchaException extends RuntimeException{
    public InvalidReCaptchaException(String responseContainsInvalidCharacters) {
    }
}
