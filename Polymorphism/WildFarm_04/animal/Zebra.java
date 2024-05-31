package Polymorphism.WildFarm_04.animal;

import Polymorphism.WildFarm_04.food.Food;

public class Zebra extends Mammal{


    public Zebra(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {
        super.setFoodEaten(food.getQuantity());
    }
}
