package pl.wieczorekp.mim.oop.mimvalley.crops;

import lombok.Getter;

public abstract class ACrop {
    @Getter
    private final String name;
    @Getter
    protected int plantTime;

    protected ACrop(int plantTime, String name) {
        this.plantTime = plantTime;
        this.name = name;
    }

    public int getAge(int curTime) {
        return curTime-plantTime;
    }

    public abstract double getCost();
    public abstract double getValue(int curTime);
}
