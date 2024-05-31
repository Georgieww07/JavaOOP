package Polymorphism.WildFarm_04;

import Polymorphism.WildFarm_04.animal.*;
import Polymorphism.WildFarm_04.food.Food;
import Polymorphism.WildFarm_04.food.Meat;
import Polymorphism.WildFarm_04.food.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animalList = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine();
            if ("End".equals(line)) {
                break;
            }
            String[] animalTokens = line.split("\\s+");

            Animal animal = null;
            switch (animalTokens[0]) {
                case "Cat":
                    animal = new Cat(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3], animalTokens[4]);
                    break;
                case "Tiger":
                    animal = new Tiger(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3]);
                    break;
                case "Mouse":
                    animal = new Mouse(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3]);
                    break;
                case "Zebra":
                    animal = new Zebra(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3]);
                    break;
            }

            animalList.add(animal);

            String[] foodTokens = scanner.nextLine().split("\\s+");

            Food food = null;

            if ("Vegetable".equals(foodTokens[0])) {
                food = new Vegetable(Integer.parseInt(foodTokens[1]));
            } else if ("Meat".equals(foodTokens[0])) {
                food = new Meat(Integer.parseInt(foodTokens[1]));
            }

            animal.makeSound();

            if (animal instanceof Mouse) {
                if (!(food instanceof Vegetable)) {
                    animal.setFoodEaten(0);
                    System.out.println("Mice are not eating that type of food!");
                }else {
                    animal.eat(food);
                }
            } else if (animal instanceof Tiger) {
                if (food instanceof Vegetable) {
                    animal.setFoodEaten(0);
                    System.out.println("Tigers are not eating that type of food!");
                }else {
                    animal.eat(food);
                }
            } else if (animal instanceof Zebra) {
                if (!(food instanceof Vegetable)) {
                    animal.setFoodEaten(0);
                    System.out.println("Zebras are not eating that type of food!");
                }else {
                    animal.eat(food);
                }
            }else {
                animal.eat(food);
            }
        }

        for (Animal animal : animalList) {
            System.out.println(animal);
        }



















































        /*int count = -1;
        Animal animal = null;
        Food food = null;
        while (true) {
            String line = scanner.nextLine();
            if ("End".equals(line)) {
                break;
            }
            count++;

            if (count % 2 == 0) {
                String[] animalTokens = line.split("\\s+");

                switch (animalTokens[0]) {
                    case "Cat":
                        animal = new Cat(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3], animalTokens[4]);
                        break;
                    case "Tiger":
                        animal = new Tiger(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3]);
                        break;
                    case "Mouse":
                        animal = new Mouse(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3]);
                        break;
                    case "Zebra":
                        animal = new Zebra(animalTokens[0], animalTokens[1], Double.parseDouble(animalTokens[2]), animalTokens[3]);
                        break;
                }

            } else {
                String[] foodTokens = line.split("\\s+");

                if ("Vegetable".equals(foodTokens[0])) {
                    food = new Vegetable(Integer.parseInt(foodTokens[1]));
                } else if ("Meat".equals(foodTokens[0])) {
                    food = new Meat(Integer.parseInt(foodTokens[1]));
                }
            }
        }

        animal.makeSound();

        if (animal instanceof Mouse) {
            if (!(food instanceof Vegetable)) {
                animal.setFoodEaten(0);
                System.out.printf("%ss are not eating that type of food!%n", animal.getClass().getSimpleName());
            }else {
                animal.eat(food);
            }
        } else if (animal instanceof Tiger) {
            if (food instanceof Vegetable) {
                animal.setFoodEaten(0);
                System.out.printf("%ss are not eating that type of food!%n", animal.getClass().getSimpleName());

            }
        } else if (animal instanceof Zebra) {
            if (!(food instanceof Vegetable)) {
                animal.setFoodEaten(0);
                System.out.printf("%ss are not eating that type of food!%n", animal.getClass().getSimpleName());
            }else {
                animal.eat(food);
            }
        }else {
            animal.eat(food);
        }
        System.out.println(animal);*/
    }
}
