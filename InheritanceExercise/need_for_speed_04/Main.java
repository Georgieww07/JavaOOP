package InheritanceExercise.need_for_speed_04;

import InheritanceExercise.need_for_speed_04.car.Car;
import InheritanceExercise.need_for_speed_04.car.FamilyCar;
import InheritanceExercise.need_for_speed_04.motorcycle.Motorcycle;

public class Main {
    public static void main(String[] args) {

        Car car = new Car(50.5, 691);
        System.out.println();

        FamilyCar familyCar = new FamilyCar(39.3, 100);
        System.out.println();

        Motorcycle motorcycle = new Motorcycle(32.4, 45);
        System.out.println();
    }
}
