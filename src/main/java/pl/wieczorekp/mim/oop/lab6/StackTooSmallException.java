package pl.wieczorekp.mim.oop.lab6;

public class StackTooSmallException extends Exception {
    public StackTooSmallException() {
        super();
    }

    public StackTooSmallException(String message) {
        super(message);
    }
}
