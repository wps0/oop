package pl.wieczorekp.mim.oop.lab3.mimvalley.farmers;

import lombok.Getter;
import pl.wieczorekp.mim.oop.lab3.mimvalley.Garden;
import pl.wieczorekp.mim.oop.lab3.mimvalley.crops.ACrop;
import pl.wieczorekp.mim.oop.lab3.mimvalley.crops.CropsFactory;

import java.util.List;
import java.util.Optional;

public abstract class AFarmer {
    @Getter
    private final String name;

    protected AFarmer(String name) {
        this.name = name;
    }

    /**
     * @param crop A crop to be planted
     * @param garden The garden in which to plant the crop
     * @param slot The slot in the garden in which to plant the crop
     * @return Cost of the operation
     */
    public double plant(ACrop crop, Garden garden, int slot, final int time) {
        assert slot < garden.getSize();
        garden.replaceCrop(slot, crop);
        System.out.printf("[%s]#%d Posadzono: %s (koszt: %.2f PLN)\n", name, time, crop.getName(), crop.getCost());
        return crop.getCost();
    }

    public Optional<ACrop> harvest(Garden garden, int slot, final int time) {
        if (garden.getCrop(slot).isEmpty()) {
            return Optional.empty();
        }
        Optional<ACrop> crop = garden.getCrop(slot);
        garden.harvestCrop(slot);
        System.out.printf("[%s]#%d Zebrano: %s (wartość: %.2f)\n", name, time, crop.get().getName(), crop.get().getValue(time));
        return crop;
    }

    public abstract double executePlantingStrategy(Garden garden, CropsFactory factory, int time);

    public abstract List<ACrop> executeHarvestingStrategy(Garden garden, int time);
}
