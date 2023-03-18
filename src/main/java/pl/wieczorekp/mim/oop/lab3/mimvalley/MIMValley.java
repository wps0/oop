package pl.wieczorekp.mim.oop.lab3.mimvalley;

import pl.wieczorekp.mim.oop.lab3.mimvalley.crops.CropsFactory;
import pl.wieczorekp.mim.oop.lab3.mimvalley.farmers.PGRFarmer;
import pl.wieczorekp.mim.oop.lab3.mimvalley.farmers.Steward;

import java.util.Random;

public class MIMValley {
    private static final int ROUNDS = 23;
    public static void main(String[] args) {
        Garden garden = new Garden(5);
        PGRFarmer pgrFarmer = new PGRFarmer("Zbyszek");
        CropsFactory cropsFactory = new CropsFactory(new Random(System.currentTimeMillis()));
        FarmingSimulator sim = new FarmingSimulator(garden, pgrFarmer, cropsFactory);
        sim.simulate(ROUNDS);

        Garden garden2 = new Garden(5);
        Steward steward = new Steward("Soplica", garden2.getSize());
        FarmingSimulator sim2 = new FarmingSimulator(garden2, steward, cropsFactory);
        sim2.simulate(ROUNDS);
    }
}
