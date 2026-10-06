
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	
    public static void main(String[] args) {
    	
    	JFrame window = new JFrame();
        window.setTitle("Blackjack: Java Edition");
        window.setLayout(new GridLayout(4, 1));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 800);
        window.setResizable(false);
        
        JPanel dealerHand = new JPanel();
        window.add(dealerHand);
        
        JPanel resultPanel = new JPanel();
        JLabel resultLabel = new JLabel();
        resultPanel.add(resultLabel);
        window.add(resultPanel);
        
        JPanel playerHand = new JPanel();
        window.add(playerHand);
        
        JPanel buttons = new JPanel();
        
        JButton hit = new JButton();
        hit.setText("Hit");
        hit.setFocusable(false);
        hit.setEnabled(false);
        buttons.add(hit);
        
        JButton stand = new JButton();
        stand.setText("Stand");
        stand.setFocusable(false);
        stand.setEnabled(false);
        buttons.add(stand);
        
        JButton next = new JButton();
        next.setText("Next");
        next.setFocusable(false);
        next.setEnabled(true);
        buttons.add(next);
        
        window.add(buttons);
    	
    	Player player = new Player();
    	Player dealer = new Player();
    	
    	Player[] players = new Player[2];
    	players[0] = player;
    	players[1] = dealer;
    	
    	Deck deck = new Deck();

        hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                player.getHand().add(deck.draw());
                playerHand.removeAll();
                for (int i = 0; i < player.getHand().size(); i++) {
                    ImageIcon card = new ImageIcon(player.getHand().get(i).toString());
                    JLabel label = new JLabel(card);
                    playerHand.add(label);
                }
                
                if (player.isBust()) {
                    hit.setEnabled(false);
                    stand.setEnabled(false);
                    resultLabel.setText("Dealer Wins");
                    next.setEnabled(true);
                }
                
                window.setVisible(true);
            }
        });
        
        stand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	hit.setEnabled(false);
            	stand.setEnabled(false);
                
                while (dealer.score() < 17) {
                    dealer.getHand().add(deck.draw());
                }
                
                dealerHand.removeAll();
                for (int i = 0; i < dealer.getHand().size(); i++) {
                    ImageIcon card = new ImageIcon(dealer.getHand().get(i).toString());
                    JLabel label = new JLabel(card);
                    dealerHand.add(label);
                }
                
                if (dealer.score() > 21 || player.score() > dealer.score()) {
                    resultLabel.setText("You Win");
                } else if (dealer.score() > player.score()) {
                	resultLabel.setText("Dealer Wins");
                } else {
                    resultLabel.setText("Tie");
        	    }
                
                next.setEnabled(true);
                window.setVisible(true);
            }
        });    
        
        next.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        		next.setEnabled(false);
        		resultLabel.setText("");
        		playerHand.removeAll();
        		dealerHand.removeAll();
        		window.repaint();
        		
        		player.reset();
        		dealer.reset();
        		deck.reset();
        		deck.shuffle();
                for (int i = 0; i < 2; i++) {
            		for (Player p : players) {
            			p.getHand().add(deck.draw());
            		}
                }
                
                for (int i = 0; i < player.getHand().size(); i++) {
                    ImageIcon card = new ImageIcon(player.getHand().get(i).toString());
                    JLabel label = new JLabel(card);
                    playerHand.add(label);
                }
                
                ImageIcon firstCard = new ImageIcon(dealer.getHand().get(0).toString());
                JLabel firstCardLabel = new JLabel(firstCard);
                dealerHand.add(firstCardLabel);
                
                ImageIcon secondCard;
                if (dealer.getHand().get(0).getValue() <= 9) {
                    secondCard = new ImageIcon("assets\\playing_cards\\red_joker.png");
                } else {
                	secondCard = new ImageIcon(dealer.getHand().get(1).toString());
                } 
                
                JLabel secondCardLabel = new JLabel(secondCard);
                dealerHand.add(secondCardLabel);  
                
                if (player.score() != 21 && dealer.score() != 21) {
                    hit.setEnabled(true);
                    stand.setEnabled(true);
                    next.setEnabled(false);
                } else {
                	if (player.score() == 21 && dealer.score() == 21) {
                		resultLabel.setText("Tie");
                	} else if (dealer.score() == 21) {
                		resultLabel.setText("Dealer Blackjack");
                	} else {
                		resultLabel.setText("Player Blackjack");
                	}
                	
                    next.setEnabled(true);
                }
                
                window.setVisible(true);
            } 
        });
 
        window.setVisible(true);
    }
}
