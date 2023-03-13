package pl.wieczorekp.mim.oop.lab3.mimvalley;

import java.util.List;

public class FarmingSimulator {
    private Garden garden;
    private AFarmer farmer;
    private List<ACrop> crops;

    public FarmingSimulator(Garden garden, AFarmer farmer, List<ACrop> crops) {
        this.garden = garden;
        this.farmer = farmer;
        this.crops = crops;
    }

    public void simulate(int duration) {
        for (int i = 1; i <= duration; i++) {

        }
    }
}
