package pl.wieczorekp.mim.oop.mimvalley.crops;

import static java.lang.Math.*;

public class TomatoCrop extends ACrop{
    private static final int MAX_VALUE = 5;
    private static final int COST = 6;

    protected TomatoCrop(int plantTime) {
        super(plantTime, "Pomidor");
    }

    @Override
    public double getCost() {
        return COST;
    }

    @Override
    public double getValue(int curTime) {
        int age = getAge(curTime);
        if (age > 14) {
            return 0;
        }
        // max value for x=5
        // https://www.desmos.com/calculator/ivfffvbfmq
        return abs((age-14) * log(age) * sin(age));
    }
}
