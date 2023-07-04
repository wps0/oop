package pl.wieczorekp.mim.oop.circuit;

import pl.wieczorekp.mim.oop.circuit.gates.Gate;
import pl.wieczorekp.mim.oop.circuit.gates.Wire;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public abstract class Bfs<T> {
    private final Circuit circuit;

    protected Bfs(Circuit circuit) {
        this.circuit = circuit;
    }

    public abstract T result();

    protected abstract void onProcessingStart(Gate g, Gate prv);

    public void run() {
        Map<Gate, Boolean> vis = new HashMap<>();
        // <the element, the element by which it was added
        Queue<Pair<Gate, Gate>> q = new ArrayDeque<>(circuit.inputGates()
                .stream()
                .map(in -> new Pair<Gate, Gate>(in, null))
                .toList());

        while (!q.isEmpty()) {
            Gate g = q.peek().first();
            Gate src = q.poll().second();

            if (vis.getOrDefault(g, false)) {
                continue;
            }
            vis.put(g, true);

            onProcessingStart(g, src);
            g.outputs().stream()
                    .map(Wire::dst)
                    .filter(gate -> !vis.getOrDefault(gate, false))
                    .map(gate -> new Pair<>(gate, g))
                    .forEach(q::add);
        }
    }
}
