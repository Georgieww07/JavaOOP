package EncapsulationExercise.PizzaCalories_04;

import java.util.HashMap;
import java.util.Map;

public class Dough {

    private static final Map<String, Double> DOUGH_TYPE = new HashMap<>();
    private static final Map<String, Double> DOUGH_TECHNIQUE = new HashMap<>();

    static {
        DOUGH_TYPE.put("White", 1.5);
        DOUGH_TYPE.put("Wholegrain", 1.0);
    }
    static{
        DOUGH_TECHNIQUE.put("Crispy", 0.9);
        DOUGH_TECHNIQUE.put("Chewy", 1.1);
        DOUGH_TECHNIQUE.put("Homemade", 1.0);
    }


    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public double calculateCalories(){
       return 2 * this.weight * DOUGH_TYPE.get(this.flourType) * DOUGH_TECHNIQUE.get(this.bakingTechnique);
    }

    private void setFlourType(String flourType) {
        if (!DOUGH_TYPE.containsKey(flourType)){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!DOUGH_TECHNIQUE.containsKey(bakingTechnique)){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200){
            throw new IllegalArgumentException("EncapsulationExercise.PizzaCalories_04.Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }
}
