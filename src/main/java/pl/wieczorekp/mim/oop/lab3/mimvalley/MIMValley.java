package pl.wieczorekp.mim.oop.lab3.mimvalley;

import java.util.ArrayList;
import java.util.Random;

public class MIMValley {
    public static void main(String[] args) {
        Garden garden = new Garden(3);
        AFarmer farmer = new PGRFarmer("Zbyszek");
        CropsFactory cropsFactory = new CropsFactory(new Random(System.currentTimeMillis()));
        FarmingSimulator sim = new FarmingSimulator(garden, farmer, cropsFactory);
        sim.simulate(20);
    }
}
