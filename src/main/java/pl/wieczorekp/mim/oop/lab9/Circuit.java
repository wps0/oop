package pl.wieczorekp.mim.oop.lab9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Circuit {
    private List<InputGate> entryPoints;
    private OutputGate outputGate;

    public Circuit() {
        this.entryPoints = new ArrayList<>();
    }

    public void addEntryPoint(InputGate gate) {
        entryPoints.add(gate);
    }

    public void setOutputGate(OutputGate outputGate) {
        this.outputGate = outputGate;
    }

    public boolean evaluate() {
        for (InputGate entryGate : entryPoints) {
            ArrayDeque<LogicGate> gates = new ArrayDeque<>();
            gates.add(entryGate);

            while (!gates.isEmpty()) {
                LogicGate next = gates.poll();
                boolean value = next.evaluate();

                for (OutputGate neighbouringGate : next.getOutputs()) {
                    neighbouringGate.addInputValue(value);

                    if (neighbouringGate.isReadyToBeEvaluated()) {
                        // add all the gates that can be evaluated
                        gates.add(neighbouringGate);
                    }
                }
            }

        }

        return outputGate.evaluate();
    }
}
