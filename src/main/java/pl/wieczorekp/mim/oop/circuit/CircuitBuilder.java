package pl.wieczorekp.mim.oop.circuit;

import pl.wieczorekp.mim.oop.circuit.gates.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CircuitBuilder<T> {
    private final Map<T, Gate> mnemonics;
    private final ArrayList<SignalEmittingGate> inputGates;
    private final TerminatingGate terminatingGate;

    public CircuitBuilder() {
        this.mnemonics = new HashMap<>();
        this.inputGates = new ArrayList<>();
        this.terminatingGate = new TerminatingGate();

        mnemonics.put(null, this.terminatingGate);
    }

    public CircuitBuilder<T> addInputGate(T mnemonic) {
        SignalEmittingGate gate = new SignalEmittingGate();
        inputGates.add(gate);
        return addGate(mnemonic, gate);
    }

    public CircuitBuilder<T> setTerminatingGate(T mnemonic) {
        if (!terminatingGate.inputs().isEmpty()) {
            Wire w = terminatingGate.inputs().get(0);
            w.dst().disconnectFrom(w);
            w.src().disconnectFrom(w);
        }

        addWire(mnemonic, null);
        return this;
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
        Circuit circuit = new Circuit();
        circuit.addInputGates(inputGates);
        circuit.setOutputGate(terminatingGate);
        return circuit;
    }

    //
    // in1 -- and1
    // in2 -/      \
    // in3 ------- or1 -- not1 -- end
    //
    public static Circuit buildSampleCircuit() {
        return new CircuitBuilder<String>()
                .addInputGate("in1")
                .addInputGate("in2")
                .addInputGate("in3")
                .addGate("or1", new OrGate())
                .addWire("in1", "or1")
                .addWire("in2", "or1")
                .addGate("and1", new AndGate())
                .addWire("in3", "and1")
                .addWire("or1", "and1")
                .addGate("not1", new NotGate())
                .addWire("and1", "not1")
                .setTerminatingGate("not1")
                .build();
    }
}
