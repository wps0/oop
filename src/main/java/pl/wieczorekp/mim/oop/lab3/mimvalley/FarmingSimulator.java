package pl.wieczorekp.mim.oop.lab3.mimvalley;

import lombok.SneakyThrows;
import pl.wieczorekp.mim.oop.lab3.mimvalley.crops.ACrop;
import pl.wieczorekp.mim.oop.lab3.mimvalley.crops.CropsFactory;
import pl.wieczorekp.mim.oop.lab3.mimvalley.farmers.AFarmer;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class FarmingSimulator {
    private final Garden garden;
    private final AFarmer farmer;
    private final CropsFactory cropsFactory;

    private double incomes;
    private double expenses;
    private List<ACrop> collectedCrops;


    public FarmingSimulator(Garden garden, AFarmer farmer, CropsFactory cropsFactory) {
        this.garden = garden;
        this.farmer = farmer;
        this.cropsFactory = cropsFactory;
        this.expenses = 0;
        this.incomes = 0;
        this.collectedCrops = new ArrayList<>();
    }

    @SneakyThrows
    public void simulate(int duration) {
        for (int i = 1; i <= duration; i++) {
            final int t = i;
            List<ACrop> localCollected = farmer.executeHarvestingStrategy(garden, i);
            double localExpenses = farmer.executePlantingStrategy(garden, cropsFactory, i);

            collectedCrops.addAll(localCollected);
            expenses += localExpenses;
            incomes += localCollected.stream()
                    .mapToDouble(crop -> crop.getValue(t))
                    .sum();

            Thread.sleep(10);
        }

        Map<String, Long> totalCrops = collectedCrops.stream()
                .collect(groupingBy(ACrop::getName, Collectors.counting()));

        System.out.printf("Podsumowanie symulacji:\n");
        System.out.printf("* czas trwania:  %d\n", duration);
        System.out.printf("* przychody   :  %.2f\n", incomes);
        System.out.printf("* wydatki     :  %.2f\n", expenses);
        System.out.printf("* zbiory      :  %d\n", totalCrops.values()
                .stream()
                .reduce(Long::sum)
                    .orElse(0L));
        totalCrops.forEach((name, cnt) -> System.out.printf("   - %s: %d\n", name, cnt));
    }
}
