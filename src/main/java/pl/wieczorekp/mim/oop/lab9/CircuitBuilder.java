package pl.wieczorekp.mim.oop.lab9;

import pl.wieczorekp.mim.oop.lab9.gates.Gate;
import pl.wieczorekp.mim.oop.lab9.gates.SignalEmittingGate;
import pl.wieczorekp.mim.oop.lab9.gates.Wire;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CircuitBuilder<T> {
    private final Circuit circuit;
    private Map<T, Gate> mnemonics;
    private ArrayList<Gate> inputGates;

    public CircuitBuilder() {
        this.circuit = new Circuit();
        this.mnemonics = new HashMap<>();
        this.inputGates = new ArrayList<>();
    }

    public CircuitBuilder<T> addInputGate(T mnemonic, SignalEmittingGate gate) {
        inputGates.add(gate);
        return addGate(mnemonic, gate);
    }

    public CircuitBuilder<T> addGate(T mnemonic, Gate gate) {
        mnemonics.putIfAbsent(mnemonic, gate);
        return this;
    }

    public CircuitBuilder<T> addGate(Gate gate) {
        throw new UnsupportedOperationException();
    }

    public CircuitBuilder<T> addWire(T from, T to) {
        Gate src = mnemonics.get(from);
        Gate dst = mnemonics.get(to);
        if (src == null) {
            throw new IllegalArgumentException("No such mnemonic as " + from);
        }
        if (dst == null) {
            throw new IllegalArgumentException("No such mnemonic as " + to);
        }

        Wire w = new Wire(src, dst);
        src.connectTo(w);
        dst.connectTo(w);

        return this;
    }

    public Circuit build() {
        throw new UnsupportedOperationException();
    }
}
