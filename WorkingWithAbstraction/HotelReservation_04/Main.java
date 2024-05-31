package WorkingWithAbstraction.HotelReservation_04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //"{pricePerDay} {numberOfDays} {season} {discountType}",
        String[] data = scanner.nextLine().split("\\s+");
        double pricePerDay = Double.parseDouble(data[0]);
        int numberOfDays = Integer.parseInt(data[1]);
        Season season = Season.valueOf(data[2].toUpperCase());
        DiscountType discountType = DiscountType.fromString(data[3]);


        PriceCalculator priceCalculator = new PriceCalculator(pricePerDay, numberOfDays, season, discountType);
        double totalSum = priceCalculator.calculatePrice(pricePerDay, numberOfDays, season, discountType);
        System.out.printf("%.2f", totalSum);
    }


}
