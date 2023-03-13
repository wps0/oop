package pl.wieczorekp.mim.oop.lab3.mimvalley;

public class CarrotCrop extends ACrop {
    protected CarrotCrop(int plantTime) {
        super(plantTime, "Marchew");
    }

    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public double getValue(int curTime) {
        double age = getAge(curTime);
        if (age > 10) {
            return 0;
        }
        // max value for age=6
        return Math.abs(age*(age - 1)*Math.sin(age)/10);
    }
}
