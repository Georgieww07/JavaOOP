package PolymorphismExercise.Vehicles_01;

public class Truck extends VehicleImpl{
    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 1.6);
    }

    @Override
    public void refuel(double liters) {
        super.setFuelQuantity(super.getFuelQuantity() + liters * 0.95);
    }
}
