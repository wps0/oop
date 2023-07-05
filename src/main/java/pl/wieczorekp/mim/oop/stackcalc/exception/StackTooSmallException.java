package pl.wieczorekp.mim.oop.stackcalc.exception;

public class StackTooSmallException extends Exception {
    public StackTooSmallException() {
        super();
    }

    public StackTooSmallException(String message) {
        super(message);
    }
}
