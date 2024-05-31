package WorkingWithAbstractionExercise.CardRank_02;

public enum CardRank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;


    public static void print(){
        System.out.println("WorkingWithAbstractionExercise.CardsWithPower_03.Card Ranks:");
        for (CardRank cardRank : CardRank.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", cardRank.ordinal(), cardRank.name());

        }
    }
}
