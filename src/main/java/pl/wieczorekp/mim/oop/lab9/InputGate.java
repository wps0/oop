package pl.wieczorekp.mim.oop.lab9;

public class InputGate implements LogicGate {
    private boolean value;

    public InputGate(boolean value) {
        this.value = value;
    }

    @Override
    public boolean isReadyToBeEvaluated() {
        return true ;
    }

    @Override
    public boolean evaluate() {
        return value;
    }
}
