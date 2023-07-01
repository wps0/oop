package pl.wieczorekp.mim.oop.lab9;

import pl.wieczorekp.mim.oop.lab9.gates.Gate;
import pl.wieczorekp.mim.oop.lab9.gates.Wire;

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
