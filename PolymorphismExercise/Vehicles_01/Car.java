package PolymorphismExercise.Vehicles_01;

public class Car extends VehicleImpl{
    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 0.9);
    }

    @Override
    public void refuel(double liters) {
        super.setFuelQuantity(super.getFuelQuantity() + liters);
    }
}
