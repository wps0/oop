package pl.wieczorekp.mim.oop.lab6;

import lombok.Getter;

public class Instruction {
    @Getter
    protected String name;

    public Instruction(String name) {
        this.name = name;
    }
}
