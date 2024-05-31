package InheritanceExercise.need_for_speed_04.car;

import InheritanceExercise.need_for_speed_04.Vehicle;

public class Car extends Vehicle {
    private static final double DEFAULT_FUEL_CONSUMPTION = 3;
    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
