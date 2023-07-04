package pl.wieczorekp.mim.oop.circuit;

import pl.wieczorekp.mim.oop.circuit.gates.Gate;
import pl.wieczorekp.mim.oop.circuit.gates.Wire;

public class ResetBfs extends Bfs<Object> {
    public ResetBfs(Circuit circuit) {
        super(circuit);
    }

    @Override
    public Object result() {
        return null;
    }

    @Override
    protected void onProcessingStart(Gate g, Gate prv) {
        g.outputs().forEach(Wire::reset);
    }
}
