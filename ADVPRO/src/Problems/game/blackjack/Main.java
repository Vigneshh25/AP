package Problems.game.blackjack;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        
        // Shuffle the deck
        deck.shuffle();
        
        // Deal and display the top 5 cards
        for (int i = 0; i < 5; i++) {
            Card card = deck.dealCard();
            System.out.println("Dealt card: " + card);
        }
    }
}
