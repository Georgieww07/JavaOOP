package Polymorphism.WildFarm_04.animal;

import Polymorphism.WildFarm_04.food.Food;

public class Tiger extends Felime{


    public Tiger(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        super.setFoodEaten(food.getQuantity());
    }
}
