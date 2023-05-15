package pl.wieczorekp.mim.oop.lab9.gates;

import lombok.extern.java.Log;
import pl.wieczorekp.mim.oop.lab9.LogicGate;

import java.util.ArrayList;

public abstract class MultipleInputsMultipleOutputsGate implements LogicGate {
    protected int activeInputs;
    protected int inactiveInputs;
    protected ArrayList<LogicGate> inputs;
    protected ArrayList<LogicGate> outputs;

    public MultipleInputsMultipleOutputsGate() {
        this.activeInputs = 0;
        this.inactiveInputs = 0;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void addInputValue(boolean value) {
        if (value) {
            activeInputs++;
        } else {
            inactiveInputs++;
        }
    }

    public void addInput(LogicGate input) {
        inputs.add(input);
    }

    public void addOutput(LogicGate output) {
        outputs.add(output);
    }

    public ArrayList<LogicGate> getOutputs() {
        return outputs;
    }

    @Override
    public boolean isReadyToBeEvaluated() {
        return activeInputs + inactiveInputs == inputs.size();
    }
}
