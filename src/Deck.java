
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cardsInDeck;
    
    public Deck() {
        
        cardsInDeck = new ArrayList<Card>();
        for (int i = 0; i < Card.suits.length; i++) {
            for (int j = 0; j < Card.ranks.length; j++) {
                Card card = new Card(Card.ranks[j], Card.suits[i]);
                cardsInDeck.add(card);
            }
        }
    }
    
    public void shuffle() {
    	
        Random generator = new Random();
        ArrayList<Card> shuffledDeck = new ArrayList<Card>();
        
        int size = cardsInDeck.size();
        for (int i = 0; i < size; i++) {
        	Card card = cardsInDeck.get(generator.nextInt(cardsInDeck.size()));
        	shuffledDeck.add(card);
        	cardsInDeck.remove(card);
        }
        
        cardsInDeck = shuffledDeck;
    }

    public Card draw() {
        Card topCard = cardsInDeck.get(0);
        cardsInDeck.remove(0);
        return topCard;
    }
    
    public ArrayList<Card> getCardsInDeck() {
        return cardsInDeck;
    }
    
    public void reset() {
    	cardsInDeck.clear();
    	for (int i = 0; i < Card.suits.length; i++) {
            for (int j = 0; j < Card.ranks.length; j++) {
                Card card = new Card(Card.ranks[j], Card.suits[i]);
                cardsInDeck.add(card);
            }
        }
    }
    
    public String toString() {
        
        StringBuilder deckString = new StringBuilder();
        
        for (int i = 0; i < cardsInDeck.size(); i++) {
            deckString = deckString.append(cardsInDeck.get(i).toString() + "\n");
        }
        
        return deckString.toString();
    }
    
}
