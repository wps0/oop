package pl.wieczorekp.mim.oop.lab3.mimvalley;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Garden {
    private List<Optional<ACrop>> crops;
    @Getter
    private int size;

    public Garden(int size) {
        this.crops = new ArrayList<>(size);
        this.size = size;
        for (int i = 0; i < size; i++) {
            crops.add(Optional.empty());
        }
    }

    public Optional<ACrop> harvestCrop(int slot) {
        return replaceCrop(slot, Optional.empty());
    }

    public Optional<ACrop> replaceCrop(int slot, ACrop newCrop) {
        return replaceCrop(slot, Optional.of(newCrop));
    }

    public Optional<ACrop> getCrop(int slot) {
        assert slot < size;
        return crops.get(slot);
    }

    private Optional<ACrop> replaceCrop(int slot, Optional<ACrop> newCrop) {
        assert slot < size;
        Optional<ACrop> oldCrop = getCrop(slot);
        crops.set(slot, newCrop);
        return oldCrop;
    }
}
