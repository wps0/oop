package pl.wieczorekp.mim.oop.lab9.gates;

public abstract class SingleInputGate extends Gate {
    @Override
    public void connectTo(Wire w) {
        if (inputs.size() == 1) {
            throw new IllegalStateException("The gate can only have a single input");
        }
        super.connectTo(w);
    }
}
