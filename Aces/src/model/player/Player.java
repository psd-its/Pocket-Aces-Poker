/**
 * 
 */
package model.player;

import model.card.Card;
import model.card.Face;
import model.card.Tup;
import model.card.WinningHands;
import model.game.Game;
import model.table.Table;


/**
 * @author Tristan s3528615
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
     * @return true if bet placed false if failed
     */
    public boolean placeBet(int bet);
    
    /**
     * Get the cards in a players hand
     * @return cards in an array
     */
    public Card[] getHand();
    
    /**
     * @return the players current balance
     */
    public int getBalance();
    
    // these functions should be void?
    /**
     * Player tries to check. Only valid if no 
     * @return true if able to check
     */
    public boolean check(Table t);
    
    /**
     * Match the current bet to stay in the game
     * @param table
     * @return boolean
     */
    public boolean call(Table t);
    
    /**
     * Raise the bet by the given amount
     * @param table
     * @param amount
     * @return boolean
     */
    public boolean raise(Table t, int amount);
    
    /**
     * Place all cash on the current hand
     * @param cash
     */
    public void allIn(int cash);
    
    /**
     * Fold your current hand
     * @return boolean 
     */
    public boolean fold();
    
    /**
     * True if the player is playing the current hand
     * False if the player has folded
     * @return boolean
     */
    public boolean isPlaying();
    
    /**
     * Get the name of the player
     * @return name
     */
    public String getName();
    
    /**
     * Get the best hand a player can make
     * @return Tuple pair winning hand and card face
     */
    public Tup<WinningHands, Face> getBestHand();
    /**
     * Set the best hand a player can make 
     * and the value 
     * @param best hand
     */
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
    
    /**
     * Defines how a player has their turn
     * @param game
     */
    public void playHand(Game g);
    
    /** 
     * money player has put into the current round of betting
     * @return 
     */
    public int getCurrentBet();

    /**
     * @param currentBet the currentBet to set
     */
    public void setCurrentBet(int currentBet);
    
}
