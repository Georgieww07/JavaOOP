package InterfacesAndAbstractionExercise.FoodShortage_04;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 4) {
                Citizen citizen = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
                personList.add(citizen);
            } else if (tokens.length == 3) {
                Rebel rebel = new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                personList.add(rebel);
            }
        }


        while (true){
            String line = scanner.nextLine();
            if ("End".equals(line)){
                break;
            }

            for (Person person : personList) {
                if (person.getName().equals(line)){
                    person.buyFood();
                }
            }
        }

       int food = personList.stream().map(Buyer::getFood).mapToInt(e -> e).sum();
        System.out.println(food);
    }
}