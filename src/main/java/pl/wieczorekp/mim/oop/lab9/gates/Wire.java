package pl.wieczorekp.mim.oop.lab9.gates;

public class Wire {
    private boolean state;
    private boolean modified;
    private final Gate src;
    private final Gate dst;

    public Wire(Gate src, Gate dst) {
        this.state = false;
        this.modified = false;
        this.src = src;
        this.dst = dst;
    }

    public boolean state() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        this.modified = true;
        dst.onStateChange(this);
    }

    public boolean modified() {
        return modified;
    }

    public Gate src() {
        return src;
    }

    public Gate dst() {
        return dst;
    }

    public void reset() {
        modified = false;
        state = false;
    }
}
