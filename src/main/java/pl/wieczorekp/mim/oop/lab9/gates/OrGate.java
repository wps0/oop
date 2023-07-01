package pl.wieczorekp.mim.oop.lab9.gates;

public class OrGate extends Gate {
    @Override
    public boolean evaluate() {
        if (!isInFullState()) {
            throw new IllegalStateException("The gate is not in a full state");
        }
        return inputs().stream()
                .map(Wire::state)
                .reduce(false, (a, b) -> a | b);
    }

}
