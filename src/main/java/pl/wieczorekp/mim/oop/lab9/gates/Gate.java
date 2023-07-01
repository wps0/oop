package pl.wieczorekp.mim.oop.lab9.gates;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Gate {
    private boolean wasPropagated;
    private int modifiedInputs;
    private boolean value;
    private final ArrayList<Wire> inputs;
    private final ArrayList<Wire> outputs;

    public Gate() {
        this.value = false;
        this.wasPropagated = false;
        this.modifiedInputs = 0;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public abstract boolean evaluate();

    public ArrayList<Wire> inputs() {
        return inputs;
    }

    public ArrayList<Wire> outputs() {
        return outputs;
    }

    public boolean value() {
        return value;
    }

    public boolean isInFullState() {
        if (modifiedInputs > inputs.size())
            throw new IllegalStateException("There's more modified inputs than input wires");
        return modifiedInputs == inputs.size();
    }

    public void propagate() {
        if (!isInFullState()) {
            throw new IllegalStateException("The gate is not in a full state");
        }
        if (wasPropagated) {
            throw new IllegalStateException("The state of the gate has been propagated before");
        }
        wasPropagated = true;
        value = evaluate();
        updateOutputWires();


        for (Wire g : outputs) {
            g.setState(value);
            if (g.dst().isInFullState()) {
                propagate();
            }
        }
    }

    public void onStateChange(Wire w) {
        if (this == w.dst()) {
            modifiedInputs++;
        }
    }

    public void connectTo(Wire w) {
        if (w.src() == this) {
            outputs.add(w);
        } else if (w.dst() == this) {
            inputs.add(w);
        } else {
            throw new IllegalArgumentException("The wire is not connected to this gate");
        }
    }

    private void updateOutputWires() {
        outputs.forEach(wire -> wire.setState(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gate gate)) return false;
        return wasPropagated == gate.wasPropagated && modifiedInputs == gate.modifiedInputs && value == gate.value
                && Objects.equals(inputs, gate.inputs) && Objects.equals(outputs, gate.outputs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wasPropagated, modifiedInputs, value, inputs, outputs);
    }
}
