import java.util.*;

public class U2 extends Rocket {

        private static int rocketU2Count;
        private final int maxCargo = 11000;
        private static final int cost = 120000000;
        private final int weight = 18000;
        private double chanceLaunched;
        private double chanceLanded;



        public U2() {
            this(0);
        }
        public U2(int lastWeight) {
            setMaxCargo(maxCargo);
            setCost(cost);
            setWeight(weight);
            setCurrentWeight(lastWeight);

            rocketU2Count++;
            System.out.println("-------------------------------------------------" + "\n... Обработка системы ..." +
                    "\n-------------------------------------------------");
        }

        public static int getRocketU2Count() {
            return rocketU2Count;
        }

        public static void setRocketU2Count(int rocketU2Count) {
            U2.rocketU2Count = rocketU2Count;
        }

        @Override
        public boolean launch() {
            System.out.println("\nЗапуск ракеты ...");
            Random randomNumber = new Random();
            double rand = randomNumber.nextDouble();
            chanceLaunched = 0.04 * getTotalWeight() / getMaxCargo();

            System.out.println("Вес : " + getCurrentWeight());
            if (chanceLaunched >= rand) {
                System.out.println("U2 Взорвана!!!");
                System.out.println("... Отправка другой ракеты, вместо взорванной ...");
                return false;
            } else {
                System.out.println("U2 отправлена успешно!");
                return true;
            }
        }

        @Override
        public boolean land() {
            System.out.println("\nПриземление...");
            Random randomNumber = new Random();
            double rand = randomNumber.nextDouble();
            chanceLanded = 0.08 * getTotalWeight() / getMaxCargo();

            if (chanceLanded >= rand) {
                System.out.println("U2 потерпела крушение!!!");
                System.out.println("... Отправка другой ракеты, вместо потерпевшей крушение ...");
                return false;
            } else {
                System.out.println("U2 приземлилась успешно!");
                return true;
            }
        }

        public static long getTotalCost() {
            return (long) getRocketU2Count() * cost;
        }

    }