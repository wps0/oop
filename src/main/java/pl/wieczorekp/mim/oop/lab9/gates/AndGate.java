package pl.wieczorekp.mim.oop.lab9.gates;

public class AndGate extends Gate {
    @Override
    public boolean evaluate() {
        if (!isInFullState()) {
            throw new IllegalStateException("The gate is not in a full state");
        }
        return inputs().parallelStream()
                .map(Wire::state)
                .reduce(true, (a, b) -> a && b);
    }
}
