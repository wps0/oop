package pl.wieczorekp.mim.oop.lab3.mimvalley;

import java.util.List;
import java.util.Optional;

public abstract class AFarmer {
    private String name;

    protected AFarmer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void plant(ACrop c, Garden g, int slot) {
        System.out.printf("Posadziłem %s za %.2f PLN\n", c.getName(), c.getCost());
    }
    public abstract Optional<ACrop> harvest(Garden g, int crop);
    public abstract Optional<List<ACrop>> tick(int time);
}
