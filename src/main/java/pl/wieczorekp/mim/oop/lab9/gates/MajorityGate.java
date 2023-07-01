package pl.wieczorekp.mim.oop.lab9.gates;

public class MajorityGate extends Gate {
    @Override
    public boolean evaluate() {
        if (!isInFullState()) {
            throw new IllegalStateException("The gate is not in a full state");
        }
        return inputs.parallelStream()
                .map(Wire::state)
                .filter(state -> state)
                .count() > inputs.size();
    }
}
