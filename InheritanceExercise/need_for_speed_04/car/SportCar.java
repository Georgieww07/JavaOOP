package InheritanceExercise.need_for_speed_04.car;

public class SportCar extends Car{
    private static final double DEFAULT_FUEL_CONSUMPTION = 10;
    public SportCar(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
