package PolymorphismExercise.Vehicles_01;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    protected VehicleImpl(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getFuelConsumption() {
        return fuelConsumption;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    @Override
    public String drive(double distance) {
        double neededFuel = distance * this.getFuelConsumption();
        String message;

        if (this.getFuelQuantity() >= neededFuel){
            this.setFuelQuantity(this.getFuelQuantity() - neededFuel);

            DecimalFormat df = new DecimalFormat("#.##");
            message = String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
        }else {
            message = String.format("%s needs refueling", this.getClass().getSimpleName());
        }
        return message;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
