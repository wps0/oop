package pl.wieczorekp.mim.oop.lab9.gates;

public abstract class NoOutputsSingleInputGate extends SingleInputGate {
    @Override
    public void connectTo(Wire w) {
        if (w.src() == this) {
            throw new IllegalStateException("The gate cannot have any outputs");
        }
        super.connectTo(w);
    }
}
