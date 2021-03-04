package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getRankInitial() {
        return rank.getInitial();
    }

    public int getRankValue() {
        return rank.getNumber();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public boolean isAce() {
        return Rank.ACE.equals(rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
