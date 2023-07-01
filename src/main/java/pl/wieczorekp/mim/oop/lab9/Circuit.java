package pl.wieczorekp.mim.oop.lab9;

import pl.wieczorekp.mim.oop.lab9.gates.Gate;
import pl.wieczorekp.mim.oop.lab9.gates.SignalEmittingGate;
import pl.wieczorekp.mim.oop.lab9.gates.TerminatingGate;

import java.util.ArrayList;
import java.util.Collection;

public class Circuit {
    private final ArrayList<SignalEmittingGate> inputGates;
    private TerminatingGate outputGate;

    public Circuit() {
        inputGates = new ArrayList<>();
    }

    public ArrayList<SignalEmittingGate> inputGates() {
        return inputGates;
    }

    public TerminatingGate outputGate() {
        return outputGate;
    }

    public void setOutputGate(TerminatingGate outputGate) {
        this.outputGate = outputGate;
    }

    public void addInputGates(Collection<SignalEmittingGate> gates) {
        this.inputGates.addAll(gates);
    }

    public int inputLength() {
        return inputGates.size();
    }

    public void reset() {
        ResetBfs resetBfs = new ResetBfs(this);
        resetBfs.run();
    }

    public int depth() {
        DepthBfs depthBfs = new DepthBfs(this, outputGate);
        depthBfs.run();
        return depthBfs.result();
    }

    public int size() {
        SizeBfs sizeBfs = new SizeBfs(this);
        sizeBfs.run();
        return sizeBfs.result();
    }

    public boolean evaluate(boolean[] input) {
        if (input.length != inputLength()) {
            throw new IllegalArgumentException("There's %d input gates, but the input array has length %d"
                    .formatted(inputLength(), input.length));
        }
        reset();

        for (int i = 0; i < input.length; i++) {
            inputGates.get(i).setSignal(input[i]);
        }

        inputGates.forEach(Gate::propagate);
        return outputGate.value();
    }
}
