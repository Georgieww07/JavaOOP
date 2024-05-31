package WorkingWithAbstraction.HotelReservation_04;

public class PriceCalculator {
    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private DiscountType discountType;

    public PriceCalculator(double pricePerDay, int numberOfDays, Season season, DiscountType discountType) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discountType = discountType;
    }

    public double calculatePrice(double pricePerDay, int numberOfDays, Season season, DiscountType discountType){
        int multiplier = season.getMultiplier();
        int reduction = discountType.getPercentage();

        double totalPrice =  pricePerDay * numberOfDays * multiplier;
        totalPrice = totalPrice - (totalPrice * (reduction * 1.00 / 100));

        return totalPrice;
    }
}
