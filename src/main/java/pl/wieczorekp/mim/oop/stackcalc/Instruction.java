package pl.wieczorekp.mim.oop.stackcalc;

import lombok.Getter;

public class Instruction {
    @Getter
    protected String name;

    public Instruction(String name) {
        this.name = name;
    }
}
