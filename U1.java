import java.util.*;

public class U1 extends Rocket {
    private static int rocketU1Count;
    private final int maxCargo = 8000;
    private static final int cost = 100000000;
    private final int weight = 10000;
    private double chanceLaunched;
    private double chanceLanded;

    public U1() {
        this(0);
    }
    public U1(int lastWeight) {
        setMaxCargo(maxCargo);
        setCost(cost);
        setWeight(weight);
        setCurrentWeight(lastWeight);

        rocketU1Count++;
        System.out.println("-------------------------------------------------" + "\n... Обработка системы ..." +
                "\n-------------------------------------------------");
    }

    public static int getRocketU1Count() {
        return rocketU1Count;
    }

    public static void setRocketU1Count(int rocketU1Count) {
        U1.rocketU1Count = rocketU1Count;
    }

    @Override
    public boolean launch() {
        System.out.println("\nЗапуск ракеты ...");
        Random randomNumber = new Random();
        double rand = randomNumber.nextDouble();
        chanceLaunched = 0.05 * getTotalWeight() / getMaxCargo();

        System.out.println("Вес : " + getCurrentWeight());
        if (chanceLaunched >= rand) {
            System.out.println("U1 Взорвана!!!");
            System.out.println("... Отправка другой ракеты, вместо взорванной ...");
            return false;
        } else {
            System.out.println("U1 отправлена успешно!");
            return true;
        }
    }

    @Override
    public boolean land() {
        System.out.println("\nПриземление...");
        Random randomNumber = new Random();
        double rand = randomNumber.nextDouble();
        chanceLanded = 0.01 * getTotalWeight() / getMaxCargo();

        if (chanceLanded >= rand) {
            System.out.println("U1 потерпела крушение!!!");
            System.out.println("... Отправка другой ракеты, вместо потерпевшей крушение ...");
            return false;
        } else {
            System.out.println("U1 приземлилась успешно!");
            return true;
        }
    }

    public static long getTotalCost() {
        return (long) getRocketU1Count() * cost;
    }

}
