package pl.wieczorekp.mim.oop.stackcalc;

public class Instruction {
    private final String name;

    public Instruction(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public boolean isParameterised() {
        return false;
    }

    @Override
    public String toString() {
        return "Instruction[" +
                "name=" + name + ']';
    }
}
