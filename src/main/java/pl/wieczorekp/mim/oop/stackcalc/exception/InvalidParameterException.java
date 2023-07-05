package pl.wieczorekp.mim.oop.stackcalc.exception;

public class InvalidParameterException extends Exception {
    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}
