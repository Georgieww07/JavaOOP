package PolymorphismExercise.VehiclesExtension_02;

public class Car extends VehicleImpl {
    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 0.9, tankCapacity);
    }
}
