package pl.wieczorekp.mim.oop.lab3.mimvalley;

public abstract class ACrop {
    private String name;
    protected int plantTime;

    protected ACrop(int plantTime, String name) {
        this.plantTime = plantTime;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract double getCost();
    public abstract double getValue(int curTime, int duration);
}
