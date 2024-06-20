package Problems.game.blackjack;

public class Card {
    public enum Suit {
        SPADES, HEARTS, CLUBS, DIAMONDS
    }
    
    public enum Rank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
        
        private final int value;
        
        Rank(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    private final Suit suit;
    private final Rank rank;
    
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    public Suit getSuit() {
        return suit;
    }
    
    public Rank getRank() {
        return rank;
    }
    
    public String toString() {
        return rank + " of " + suit;
    }
}
