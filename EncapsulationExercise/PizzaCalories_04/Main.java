package EncapsulationExercise.PizzaCalories_04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaInfo = scanner.nextLine().split("\\s+");
        String[] doughInfo = scanner.nextLine().split("\\s+");

        Pizza pizza;
        Dough dough;
        try{
            pizza = new Pizza(pizzaInfo[1], Integer.parseInt(pizzaInfo[2]));
            dough = new Dough(doughInfo[1], doughInfo[2], Double.parseDouble(doughInfo[3]));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        pizza.setDough(dough);


        while (true){
            String command = scanner.nextLine();
            if ("END".equals(command)){
                break;
            }

            String[] toppingInfo = command.split("\\s+");
            Topping topping;
            try{
                topping = new Topping(toppingInfo[1], Double.parseDouble(toppingInfo[2]));
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                return;
            }

            pizza.addTopping(topping);
        }

        if (pizza.getToppings().size() > 0){
            System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
        }

    }
}
