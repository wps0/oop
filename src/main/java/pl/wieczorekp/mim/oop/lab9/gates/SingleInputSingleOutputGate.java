package pl.wieczorekp.mim.oop.lab9.gates;

import pl.wieczorekp.mim.oop.lab9.LogicGate;

public abstract class SingleInputSingleOutputGate extends MultipleInputsMultipleOutputsGate {
    @Override
    public void addInput(LogicGate input) {
        if (!inputs.isEmpty()) {
            throw new IllegalStateException("Input is already set");
        }
        super.addInput(input);
    }

    @Override
    public void addOutput(OutputGate output) {
        if (!outputs.isEmpty()) {
            throw new IllegalStateException("Output is already set");
        }
        super.addOutput(output);
    }
}
