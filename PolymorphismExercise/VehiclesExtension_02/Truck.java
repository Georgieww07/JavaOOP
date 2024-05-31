package PolymorphismExercise.VehiclesExtension_02;

public class Truck extends VehicleImpl {
    private final static double REFUEL_PERCENTAGE = 0.95;
    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 1.6, tankCapacity);
    }

    @Override
    public void refuel(double liters) {
        liters *= REFUEL_PERCENTAGE;
        super.refuel(liters);
    }
}
