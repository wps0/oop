package pl.wieczorekp.mim.oop.lab3.mimvalley;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PGRFarmer extends AFarmer {
    protected PGRFarmer(String name) {
        super("Pracownik PGR");
    }

    @Override
    public double executePlantingStrategy(Garden garden, CropsFactory factory, int time) {
        if (time % 10 != 1)
            return 0;

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
        if (time % 10 != 1)
            return List.of();
        List<ACrop> harvested = new ArrayList<>();
        for (int i = 0; i < garden.getSize(); i++) {
            Optional<ACrop> crop = harvest(garden, i, time);
            crop.ifPresent(harvested::add);
        }
        return harvested;
    }
}
