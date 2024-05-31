package InterfacesAndAbstractionExercise.BirthdayCelebrations_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Birthable> birthableList = new ArrayList<>();

        while (true){
            String line = scanner.nextLine();
            if ("End".equals(line)){
                break;
            }
            String[] tokens = line.split("\\s+");

            switch (tokens[0]){
                case "Pet":
                    Pet pet = new Pet(tokens[1], tokens[2]);
                    birthableList.add(pet);
                    break;
                case "InterfacesAndAbstractionExercise.FoodShortage_04.Citizen":
                    Citizen citizen = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                    birthableList.add(citizen);
                    break;
            }
        }
        String year = scanner.nextLine();

        birthableList.stream()
                .filter(birthable -> birthable.getBirthDate().endsWith(year))
                .forEach(birthable -> System.out.println(birthable.getBirthDate()));
    }
}
