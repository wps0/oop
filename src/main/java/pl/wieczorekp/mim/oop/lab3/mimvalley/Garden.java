package pl.wieczorekp.mim.oop.lab3.mimvalley;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Garden {
    private List<ACrop> crops;
    private int size;

    public Garden(int size) {
        this.crops = new ArrayList<>(size);
        this.size = size;
    }

    public Optional<ACrop> replaceCrop(int slot, ACrop crop) {
        assert slot < size;
        Optional<ACrop> oldCrop = getCrop(slot);
        crops.set(slot, crop);
        return oldCrop;
    }
    public Optional<ACrop> getCrop(int slot) {
        assert slot < size;
        return Optional.ofNullable(crops.get(slot));
    }
}
