
public class Card {
    
    public static final String[] ranks = {"ace", "king", "queen", "jack", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
    public static final String[] suits = {"spades", "hearts", "clubs", "diamonds"};
    
    private String rank;
    private String suit;
    private int value;
    
    public Card(String rank, String suit) {
        
        this.rank = rank;
        this.suit = suit;
        
        if (this.rank.equals("ace")) {
            value = 11;
        } else if (this.rank.equals("king") || this.rank.equals("queen") || this.rank.equals("jack")) {
            value = 10;
        } else {
            value = Integer.parseInt(this.rank);
        }
    }
    
    public String getRank() {
        return rank;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public String toString() {
        return "assets\\playing_cards\\" + rank + "_of_" + suit + ".png";
    }
    
}
