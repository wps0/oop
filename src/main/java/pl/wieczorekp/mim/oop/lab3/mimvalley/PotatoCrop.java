package pl.wieczorekp.mim.oop.lab3.mimvalley;

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
        if (curTime-plantTime <= MAX_VALUE)
            return curTime-plantTime;
        return 0;
    }
}
