package pl.wieczorekp.mim.oop.lab9;

import pl.wieczorekp.mim.oop.lab9.gates.Gate;
import pl.wieczorekp.mim.oop.lab9.gates.TerminatingGate;

import java.util.HashMap;
import java.util.Map;

public class DepthBfs extends Bfs<Integer> {
    private Map<Gate, Integer> dist;
    private TerminatingGate dst;

    public DepthBfs(Circuit circuit, TerminatingGate dst) {
        super(circuit);
        this.dist = new HashMap<>();
        this.dst = dst;
    }

    @Override
    public Integer result() {
        return dist.getOrDefault(dst, Integer.MAX_VALUE);
    }

    @Override
    protected void onProcessingStart(Gate g, Gate prv) {
        dist.put(g, Integer.min(dist.getOrDefault(g, Integer.MAX_VALUE), dist.getOrDefault(prv, -1) + 1));
    }
}
