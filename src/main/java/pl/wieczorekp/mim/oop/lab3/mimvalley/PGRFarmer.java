package pl.wieczorekp.mim.oop.lab3.mimvalley;

import java.util.List;
import java.util.Optional;

public class PGRFarmer extends AFarmer {
    protected PGRFarmer(String name) {
        super("Pracownik PGR");
    }

    @Override
    public void plant(ACrop c, Garden g, int slot) {

    }

    @Override
    public Optional<ACrop> harvest(Garden g, int crop) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ACrop>> tick(int time) {
        return Optional.empty();
    }
}
