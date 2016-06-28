/**
 * 
 */
package model.player;

import model.card.Card;
import model.table.Table;


/**
 * @author deep_thought
 *
 */
public interface Player 
{
    /**
     * Constant for starting balance
     */
    public static final int START_BALANCE = 10000;
    
    /**
     * 
     * @param bet
     * @return true if bet placed falce if failed
     */
    public void placeBet(int bet);
    
    /**
     * Get the cards in a players hand
     * @return 2 cards in an array
     */
    public Card[] getHand();
    
    /**
     * @return the players current balance
     */
    public int getBalance();
    
    // these functions should be void?
    /**
     * Player trys to check. Only valid if no 
     * @return true if able to check
     */
    public boolean check(Table t);
    
    public boolean call(int bet);
    
    public void raise(int ammount);
    
    public boolean allIn(int cash);
    
    public boolean fold();
    
    public boolean isPlaying();
    
    public String getName();

   
}
