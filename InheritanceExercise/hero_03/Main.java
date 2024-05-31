package InheritanceExercise.hero_03;

public class Main {
    public static void main(String[] args) {

        Hero hero = new Hero("Gosho", 1);
        System.out.println(hero);

        Elf elf = new Elf("Mityo", 12);
        System.out.println(elf);

        BladeKnight bladeKnight = new BladeKnight("Tosho", 17);
        System.out.println(bladeKnight);
    }
}
