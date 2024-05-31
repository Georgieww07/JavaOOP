package InheritanceExercise.animals_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        while (true) {
            String command = scanner.nextLine();
            if ("Beast!".equals(command)) {
                break;
            }
            String[] animalInfo = scanner.nextLine().split("\\s+");

            try{
                switch (command) {
                    case "Polymorphism.Animals_03.Cat":
                        Cat cat = new Cat(animalInfo[0], Integer.parseInt(animalInfo[1]), animalInfo[2]);
                        animals.add(cat);

                        break;
                    case "Polymorphism.Animals_03.Dog":
                        Dog dog = new Dog(animalInfo[0], Integer.parseInt(animalInfo[1]), animalInfo[2]);
                        animals.add(dog);

                        break;
                    case "Frog":
                        Frog frog = new Frog(animalInfo[0], Integer.parseInt(animalInfo[1]), animalInfo[2]);
                        animals.add(frog);

                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(animalInfo[0], Integer.parseInt(animalInfo[1]));
                        animals.add(kitten);

                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(animalInfo[0], Integer.parseInt(animalInfo[1]));
                        animals.add(tomcat);
                        break;
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }

        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

}
