package pl.wieczorekp.mim.oop.lab9.gates;

public class TerminatingGate extends NoOutputsSingleInputGate {
    @Override
    public boolean evaluate() {
        if (!isInFullState()) {
            throw new IllegalStateException("The gate is not in a full state");
        }
        return inputs().get(0).state();
    }
}
