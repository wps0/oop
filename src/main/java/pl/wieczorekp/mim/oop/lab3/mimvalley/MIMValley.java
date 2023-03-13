package pl.wieczorekp.mim.oop.lab3.mimvalley;

import java.util.ArrayList;

public class MIMValley {
    public static void main(String[] args) {
        Garden garden = new Garden(3);
        AFarmer farmer;
        FarmingSimulator sim = new FarmingSimulator(garden, farmer, crops);
        sim.simulate(10);
    }
}
