package pl.wieczorekp.mim.oop.stackcalc;

import lombok.Getter;

public class ParametrizedInstruction<T> extends Instruction {
    @Getter
    private T param;

    public ParametrizedInstruction(String name) {
        super(name);
    }

    public ParametrizedInstruction(String name, T param) {
        super(name);
        this.param = param;
    }
}
