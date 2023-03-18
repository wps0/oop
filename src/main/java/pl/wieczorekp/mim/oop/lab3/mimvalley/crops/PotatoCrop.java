package pl.wieczorekp.mim.oop.lab3.mimvalley.crops;

public class PotatoCrop extends ACrop {
    private static final int MAX_VALUE = 5;
    private static final int COST = 3;
    protected PotatoCrop(int time) {
        super(time,"Ziemniaki");
    }

    @Override
    public double getCost() {
        return COST;
    }

    @Override
    public double getValue(int curTime) {
        if (getAge(curTime) <= MAX_VALUE)
            return getAge(curTime);
        return 0;
    }
}
