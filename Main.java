import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        final int phase1 = 1;
        final int phase2 = 2;

        long u1Cost;
        long u2Cost;
        int phase1Rockets;
        int phase2Rockets;

        ArrayList<Item> phase1Item;
        ArrayList<Item> phase2Item;

        Simulation simulation = new Simulation();

        System.out.println("Загрузка ........................................");
        phase1Item = simulation.loadItems(phase1);
        phase2Item = simulation.loadItems(phase2);

        System.out.println("Начало симуляции для ракет U1 ...\n");
        System.out.println("................ Симуляция Фазы 1 ................");
        simulation.runSimulation(simulation.loadU1(phase1Item), 1);
        System.out.println("........... Симуляция выполнена успешна ...........\n");
        phase1Rockets = U1.getRocketU1Count();
        System.out.println("Ракеты использованые в Фазе 1: " + phase1Rockets);

        System.out.println(".......... Симуляция Фазы 2 ..........");
        simulation.runSimulation(simulation.loadU1(phase2Item), 1);
        System.out.println(".......... Симуляция выполнена успешна ..........\n");
        phase2Rockets = U1.getRocketU1Count() - phase1Rockets;
        System.out.println("Ракеты использованые в Фазе 2: " + phase2Rockets + "\n");

        System.out.println("Итого " + U1.getRocketU1Count() + " ракет использовано.");
        u1Cost = totalCost(1);

        System.out.println("Начало симуляции для ракет U2 ...\n");
        System.out.println(".......... Симуляция Фазы 1 ..........");
        simulation.runSimulation(simulation.loadU2(phase1Item), 2);
        System.out.println(".......... Симуляция выполнена успешна ..........\n");
        phase1Rockets = U2.getRocketU2Count();
        System.out.println("Ракеты использованые в Фазе 1: " + phase1Rockets);

        System.out.println(".......... Симуляция Фазы 2 ..........");
        simulation.runSimulation(simulation.loadU2(phase2Item), 2);
        System.out.println(".......... Симуляция выполнена успешна ..........\n");
        phase2Rockets = U2.getRocketU2Count() - phase1Rockets;
        System.out.println("Ракеты использованые в Фазе 2: " + phase2Rockets);

        System.out.println("Итого " + U2.getRocketU2Count() + " ракет использовано." + "\n");
        u2Cost = totalCost(2);

        System.out.println("\nФинансовые затраты для ракет U1 составили : " + u1Cost/1000000 +  " Млн. $");
        System.out.println("Финансовые затраты для ракет U2 составили : " + u2Cost/1000000 +  " Млн. $");

        if (u1Cost > u2Cost) {
            System.out.println("В данной симуляции использование ракет U2, вышло дешевле.");
        } else {
            System.out.println("В данной симуляции использование ракет U1, вышло дешевле.");
        }

    }

    public static long totalCost(int i) {
        return (i == 1) ? U1.getTotalCost() : U2.getTotalCost();
    }
}