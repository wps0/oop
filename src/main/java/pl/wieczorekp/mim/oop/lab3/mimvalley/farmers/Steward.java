package pl.wieczorekp.mim.oop.lab3.mimvalley.farmers;

import pl.wieczorekp.mim.oop.lab3.mimvalley.Garden;
import pl.wieczorekp.mim.oop.lab3.mimvalley.crops.ACrop;
import pl.wieczorekp.mim.oop.lab3.mimvalley.crops.CropsFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Steward extends AFarmer {
    private double[] cropValues;

    public Steward(String name, int gardenSize) {
        super(name);
        this.cropValues = new double[gardenSize];
        for (int i = 0; i < gardenSize; i++) {
            this.cropValues[i] = 0;
        }
    }

    @Override
    public double executePlantingStrategy(Garden garden, CropsFactory factory, int time) {
        double cost = 0;
        for (int i = 0; i < garden.getSize(); i++) {
            if (garden.getCrop(i).isEmpty()) {
                ACrop crop = factory.createRandomCrop(time);
                cost += plant(crop, garden, i, time);
            }
        }
        return cost;
    }

    @Override
    public List<ACrop> executeHarvestingStrategy(Garden garden, int time) {
        List<ACrop> harvested = new ArrayList<>();
        for (int i = 0; i < garden.getSize(); i++) {
            Optional<ACrop> c = garden.getCrop(i);
            if (c.isEmpty()) {
                continue;
            }
            double cropVal = c.get().getValue(time);
            if (cropValues[i] > cropVal) {
                harvested.add(harvest(garden, i, time).get());
                cropValues[i] = 0;
            } else {
                cropValues[i] = cropVal;
            }
        }
        return harvested;
    }
}
