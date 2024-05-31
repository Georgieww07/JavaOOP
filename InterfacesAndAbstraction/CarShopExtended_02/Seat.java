package InterfacesAndAbstraction.CarShopExtended_02;

public class Seat extends CarImpl implements Sellable {
    private Double price;

    public Seat(String model, String color, Integer horsepower, String countryProduced, Double price){
        super(model, color, horsepower, countryProduced);
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Leon sells for %f", this.price);
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
