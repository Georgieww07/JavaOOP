package Polymorphism.Animals_03;

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat("Oscar", "Whiskas");
        Animal dog = new Dog("Rocky", "Polymorphism.WildFarm.food.Meat");
        System.out.println(cat.explainSelf());
        System.out.println(dog.explainSelf());
    }

}
