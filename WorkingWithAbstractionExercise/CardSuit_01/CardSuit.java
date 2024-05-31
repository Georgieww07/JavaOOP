package WorkingWithAbstractionExercise.CardSuit_01;

public enum CardSuit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;


    public static void print(){
        System.out.println("WorkingWithAbstractionExercise.CardsWithPower_03.Card Suits:");
        for (CardSuit card : CardSuit.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", card.ordinal(), card.name());

        }
    }
}
