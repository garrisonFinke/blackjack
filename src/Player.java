
import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand;

	public Player() {
		hand = new ArrayList<Card>();
	}
	
	public int score() {
        
        int score = 0;
        for (int i = 0; i < hand.size(); i++) {
            score = score + hand.get(i).getValue();
        }
        
        if (score > 21) {
        	
            for (int i = 0; i < hand.size(); i++) {
            	
                if (hand.get(i).getRank().equals("ace")) {
                    score = score - 10;
                }
                
                if (score <= 21) {
                    break;
                }
            }
        }
        
        return score;
    }
	
	public boolean isBust() {
        return score() > 21;
    }
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public void reset() {
		hand = new ArrayList<Card>();
	}
	
	 public String toString() {
	        
	        StringBuilder playerString = new StringBuilder();
	        
	        for (int i = 0; i < hand.size(); i++) {
	            playerString = playerString.append(hand.get(i).toString() + "\n");
	        }
	        
	        return playerString.toString();
	    }
}
