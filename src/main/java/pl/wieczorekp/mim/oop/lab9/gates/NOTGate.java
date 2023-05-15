package pl.wieczorekp.mim.oop.lab9.gates;

public class NOTGate extends SingleInputSingleOutputGate {
    @Override
    public boolean evaluate() {
        if (!isReadyToBeEvaluated()) {
            throw new IllegalStateException();
        }
        return this.activeInputs == 0;
    }
}
