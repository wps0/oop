package pl.wieczorekp.mim.oop.circuit.gates;

public class MajorityGate extends Gate {
    @Override
    public boolean evaluate() {
        if (!isInFullState()) {
            throw new IllegalStateException("The gate is not in a full state");
        }
        long trueInputs = inputs().parallelStream()
                .map(Wire::state)
                .filter(state -> state)
                .count();
        return trueInputs > inputs().size() / 2;
    }
}
