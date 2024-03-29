package pl.wieczorekp.mim.oop.circuit.gates;

public abstract class NoInputsGate extends Gate {
    @Override
    public void connectTo(Wire w) {
        if (w.dst() == this) {
            throw new IllegalStateException("The gate cannot have any input wires");
        }
        super.connectTo(w);
    }
}
