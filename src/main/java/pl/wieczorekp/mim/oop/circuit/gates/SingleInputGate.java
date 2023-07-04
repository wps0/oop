package pl.wieczorekp.mim.oop.circuit.gates;

public abstract class SingleInputGate extends Gate {
    @Override
    public void connectTo(Wire w) {
        if (inputs().size() == 1 && w.dst() == this) {
            throw new IllegalStateException("The gate can only have a single input");
        }
        super.connectTo(w);
    }
}
