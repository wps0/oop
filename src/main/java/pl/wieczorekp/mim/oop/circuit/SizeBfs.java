package pl.wieczorekp.mim.oop.circuit;

import pl.wieczorekp.mim.oop.circuit.gates.Gate;

public class SizeBfs extends Bfs<Integer> {
    private int size;

    public SizeBfs(Circuit circuit) {
        super(circuit);
        this.size = 0;
    }

    @Override
    public Integer result() {
        return size;
    }

    @Override
    protected void onProcessingStart(Gate g, Gate prv) {
        size++;
    }
}
