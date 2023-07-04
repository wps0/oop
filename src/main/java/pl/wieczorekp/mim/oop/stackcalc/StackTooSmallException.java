package pl.wieczorekp.mim.oop.stackcalc;

public class StackTooSmallException extends Exception {
    public StackTooSmallException() {
        super();
    }

    public StackTooSmallException(String message) {
        super(message);
    }
}
