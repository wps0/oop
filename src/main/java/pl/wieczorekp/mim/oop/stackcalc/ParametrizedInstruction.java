package pl.wieczorekp.mim.oop.stackcalc;

public final class ParametrizedInstruction<T> extends Instruction {
    private T param;

    public ParametrizedInstruction(String name) {
        super(name);
        this.param = null;
    }

    public ParametrizedInstruction(String name, T param) {
        super(name);
        this.param = param;
    }

    public T param() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    @Override
    public boolean isParameterised() {
        return true;
    }

    @Override
    public String toString() {
        return "ParametrizedInstruction[" +
                "name=" + name() + ", " +
                "param=" + param + ']';
    }

}
