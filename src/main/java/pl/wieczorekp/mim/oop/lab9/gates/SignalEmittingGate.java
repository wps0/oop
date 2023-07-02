package pl.wieczorekp.mim.oop.lab9.gates;

public class SignalEmittingGate extends NoInputsGate {
    private boolean signal;

    public SignalEmittingGate() {
    }

    public SignalEmittingGate(boolean signal) {
        this.signal = signal;
    }

    public void setSignal(boolean value) {
        this.signal = value;
    }

    @Override
    public boolean evaluate() {
        return signal;
    }
}
