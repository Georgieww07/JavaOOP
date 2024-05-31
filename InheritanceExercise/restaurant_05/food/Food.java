package InheritanceExercise.restaurant_05.food;

import InheritanceExercise.restaurant_05.Product;

import java.math.BigDecimal;

public class Food extends Product {
    private double grams;
    public Food(String name, BigDecimal price, double grams){
        super(name, price);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }
}
