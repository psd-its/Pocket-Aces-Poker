/**
 * 
 */
package model.player;

import model.card.Card;
import model.card.Face;
import model.card.Tup;
import model.card.WinningHands;
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
    public boolean placeBet(int bet);
    
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
    
    public boolean call(Table t);
    
    public boolean raise(Table t, int ammount);
    
    public void allIn(int cash);
    
    public boolean fold();
    
    public boolean isPlaying();
    
    public String getName();
    
    public Tup<WinningHands, Face> getBestHand();
    
    public void setBestHand(Tup<WinningHands, Face> best);
    
    /**
     * Set players cards for test purposes
     * @param cards
     */
    public void setHand(Card[] cards);
    
    /**
     * Player is all in. This is used to for when a player 
     * is all in but they don't meet the min round bet
     * @return true if player is all in
     */
    public boolean isAllIn();
    
    /**
     * Set the players all in status
     * @param boolean
     */
    public void setAllIn(boolean allIn);
    
    /**
     * Update the players cash
     * @param amount
     */
    public void addCash(int amount);

   
}
