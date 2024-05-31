package WorkingWithAbstraction.HotelReservation_04;

public enum DiscountType {

    VIP(20),
    SECOND_VISIT(10),
    NONE(1);

    private int percentage;

    DiscountType(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public static DiscountType fromString(String dt){
        DiscountType discountType = null;
        switch (dt) {
            case "VIP":
                discountType = DiscountType.VIP;
                break;
            case "SecondVisit":
                discountType = DiscountType.SECOND_VISIT;
                break;
            case "None":
                discountType = DiscountType.NONE;
                break;
        }
        return discountType;

    }
}
