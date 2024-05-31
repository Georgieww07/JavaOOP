package EncapsulationExercise.PizzaCalories_04;

import java.util.HashMap;
import java.util.Map;

public class Topping {
    private static final Map<String, Double> TOPPING_TYPE = new HashMap<>();
    static {
        TOPPING_TYPE.put("Polymorphism.WildFarm.food.Meat", 1.2);
        TOPPING_TYPE.put("Veggies", 0.8);
        TOPPING_TYPE.put("Cheese", 1.1);
        TOPPING_TYPE.put("Sauce", 0.9);
    }
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {

        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public double calculateCalories(){
       return 2 * this.weight * TOPPING_TYPE.get(this.toppingType);
    }

    private void setToppingType(String toppingType) {
        if (!TOPPING_TYPE.containsKey(toppingType)){
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50){
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.toppingType));
        }
        this.weight = weight;
    }
}
