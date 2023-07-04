package pl.wieczorekp.mim.oop.circuit.gates;

public class ParityGate extends Gate {
    @Override
    public boolean evaluate() {
        if (!isInFullState()) {
            throw new IllegalStateException("The gate is not in a full state");
        }
        return inputs().parallelStream()
                .map(Wire::state)
                .filter(state -> state)
                .count() % 2 == 1;
    }
}
