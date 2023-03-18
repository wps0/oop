package pl.wieczorekp.mim.oop.lab3.mimvalley.crops;

import java.util.Random;

public class CropsFactory {
    private final Random rng;
    private final int cropCount;

    public CropsFactory(Random rng) {
        this.rng = rng;
        this.cropCount = 3;
    }

    public PotatoCrop createPotatoCrop(int time) {
        return new PotatoCrop(time);
    }

    public CarrotCrop createCarrotCrop(int time) {
        return new CarrotCrop(time);
    }

    public TomatoCrop createTomatoCrop(int time) {
        return new TomatoCrop(time);
    }


    public ACrop createRandomCrop(int time) {
        int cropId = rng.nextInt(cropCount);
        return switch (cropId) {
            case 0 -> createPotatoCrop(time);
            case 1 -> createCarrotCrop(time);
            case 2 -> createTomatoCrop(time);
            default -> throw new IllegalStateException("Unexpected value: " + cropId);
        };
    }
}
