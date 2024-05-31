package Polymorphism.Animals_03;

public class Dog extends Animal {
    private static final String SOUND = "DFAAJ";

    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(SOUND);
        return sb.toString();
    }
}
