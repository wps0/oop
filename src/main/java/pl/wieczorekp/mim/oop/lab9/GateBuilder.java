package pl.wieczorekp.mim.oop.lab9;

import pl.wieczorekp.mim.oop.lab9.gates.Gate;

public class GateBuilder {
    private Gate dst;

    public GateBuilder setDestination(Gate dst) {
        this.dst = dst;
        return this;
    }

    public Gate build(Class<? extends Gate> gate) {
        return null;
    }
}
