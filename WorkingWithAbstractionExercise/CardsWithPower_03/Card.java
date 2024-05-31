package WorkingWithAbstractionExercise.CardsWithPower_03;

import WorkingWithAbstractionExercise.CardsWithPower_03.CardRank;
import WorkingWithAbstractionExercise.CardsWithPower_03.CardSuit;

public class Card {
    private CardRank cardRank;
    private CardSuit cardSuit;

    public Card(CardRank rankPower, CardSuit suitPower) {
        this.cardRank = rankPower;
        this.cardSuit = suitPower;
    }

    public int getPower(){
        return this.cardRank.getPower() + this.cardSuit.getPower();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.cardRank.name(), this.cardSuit.name(), getPower());
    }
}
