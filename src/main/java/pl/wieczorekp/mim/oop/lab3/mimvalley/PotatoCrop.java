package pl.wieczorekp.mim.oop.lab3.mimvalley;

public class PotatoCrop extends ACrop {
    private static final int MAX_VALUE = 5;
    protected PotatoCrop(int time) {
        super(time,"Ziemniaki");
    }

    @Override
    public double getValue(int curTime, int duration) {
        if (curTime-plantTime <= MAX_VALUE)
            return curTime-plantTime;
        return 0;
    }
}
