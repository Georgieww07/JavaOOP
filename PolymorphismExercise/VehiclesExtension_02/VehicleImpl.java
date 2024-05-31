package PolymorphismExercise.VehiclesExtension_02;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    protected VehicleImpl(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    @Override
    public String drive(double distance) {
        double neededFuel = distance * this.fuelConsumption;
        String message;

        if (this.getFuelQuantity() >= neededFuel){
            this.fuelQuantity -= neededFuel;
            DecimalFormat df = new DecimalFormat("##.##");
            message = String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
        }else {
            message = String.format("%s needs refueling", this.getClass().getSimpleName());
        }
        return message;
    }

    @Override
    public void refuel(double liters) {
        if (liters > 0){
            double newFuelQuantity = this.fuelQuantity + liters;
            if (this.tankCapacity >= newFuelQuantity){
                this.setFuelQuantity(this.getFuelQuantity() + liters);
            }else {
                throw new IllegalArgumentException("Cannot fit fuel in tank");
            }
        }else {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
