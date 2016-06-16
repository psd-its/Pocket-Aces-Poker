/**
 * 
 */
package model.player;

import model.card.Card;


/**
 * @author deep_thought
 *
 */
public interface Player 
{
    public static final int START_BALANCE = 10000;
    
    public boolean placeBet(int bet);
    
    public Card[] getHand();
    
    public int getBalance();
    
    // these functions should be void?
    // as the displaying of these options is the validation
    public boolean check();
    
    public boolean call();
    
    public boolean raise(int ammount);
    
    public boolean allIn();
    
    public boolean fold();
    
    public boolean isPlaying();
}
