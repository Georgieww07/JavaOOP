package InheritanceExercise.need_for_speed_04.motorcycle;

public class RaceMotorcycle extends Motorcycle{
    private static final double DEFAULT_FUEL_CONSUMPTION = 8;
    public RaceMotorcycle(double fuel, int horsepower){
        super(fuel, horsepower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
