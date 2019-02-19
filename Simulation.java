import java.io.*;
import java.util.*;

public class Simulation {

        private ArrayList<Item> items;
        private ArrayList<Rocket> rocketU1;
        private ArrayList<Rocket> rocketU2;
        private boolean hasLanded = true;
        private File phase1 = new File("phase-1.txt");
        private File phase2 = new File("phase-2.txt");

        public Simulation() {
        }

        public ArrayList<Item> loadItems(int phase) throws FileNotFoundException {

            if (phase == 1) {
                System.out.println("Загрузка Фазы 1");
                loadItems(phase1);
                System.out.println("Фаза 1 успешно загружена! ");
            } else {
                System.out.println("Загрузка фазы 2");
                loadItems(phase2);
                System.out.println("Фаза 2 успешно загружена! \n");
            }

            return items;
        }

        public void loadItems(File file) throws FileNotFoundException {
            items = new ArrayList<>();
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String string = fileScanner.nextLine();
                String[] separated = string.split("=");

                Item item = new Item(separated[0], Integer.parseInt(separated[1]));
                items.add(item);
            }
        }

        public ArrayList<Rocket> loadU1(List<Item> itemsU1) {
            System.out.println("Загрузка ракеты U1 ...");
            rocketU1 = new ArrayList<>();
            Rocket rocket = new U1();
            Iterator iterator = itemsU1.iterator();

            while (iterator.hasNext()) {
                Item item = (Item) iterator.next();
                if (rocket.canCarry(item)) {
                    rocket.carry(item);
                } else {
                    rocketU1.add(rocket);
                    rocket = new U1();
                    System.out.println("Новая ракета U1 создана.");
                    rocket.carry(item);
                }
                if (!iterator.hasNext()) {
                    rocketU1.add(rocket);
                }
            }

            return rocketU1;
        }

        public ArrayList<Rocket> loadU2(ArrayList<Item> itemsU2) {
            System.out.println("Загрузка ракеты U2 ...");
            rocketU2 = new ArrayList<>();
            Rocket rocket = new U2();
            Iterator iterator = itemsU2.iterator();

            while (iterator.hasNext()) {
                Item item = (Item) iterator.next();
                if (rocket.canCarry(item)) {
                    rocket.carry(item);
                } else {
                    rocketU2.add(rocket);
                    rocket = new U2();
                    System.out.println("Новая ракета U1 создана.");
                    rocket.carry(item);
                }
                if (!iterator.hasNext()) {
                    rocketU2.add(rocket);
                }
            }

            return rocketU2;
        }

        public void runSimulation(ArrayList<Rocket> rockets, int i) {

            for (Rocket rocket : rockets) {

                while (!rocket.launch()) {
                    launchSimulation(i);
                }

                while (!rocket.land()) {
                    while (!rocket.launch()) {
                        launchSimulation(i);
                    }
                    landSimulation(i);
                }

            }
        }

        public void launchSimulation(int i) {

            if (i == 1) {
                int counter1 = U1.getRocketU1Count();
                counter1++;
                U1.setRocketU1Count(counter1);

            } else {
                int counter1 = U2.getRocketU2Count();
                counter1++;
                U2.setRocketU2Count(counter1);

            }
        }

        public void landSimulation(int i) {
            if (i == 1) {
                int counter = U1.getRocketU1Count();
                counter++;
                U1.setRocketU1Count(counter);

            } else {
                int counter = U2.getRocketU2Count();
                counter++;
                U2.setRocketU2Count(counter);
            }
            hasLanded = false;
        }
    }
