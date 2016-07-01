package model.player;

import model.card.Card;
import model.card.Face;
import model.card.Tup;
import model.card.WinningHands;

public abstract class AbsPlayer implements Player
{
    // This holds the ammount the player has staked
    //in the current round of betting. It is moved into the 
    // pot before the next round of betting starts
    protected int currentBet;
    // players name
    protected String name;
    // is the player human or AI
    protected boolean human;
    // is the player still in the current hand 
    // returns false of they fold
    protected boolean playingHand;
    // Players total remaining credit
    protected int cash;
    // Cards in the players hand
    protected Card hand[];
    // Is the best hand a player can make along with
    // the high card value (face) 
    protected Tup<WinningHands, Face> bestHand;
   // protected Face highCard; @deprecated
    protected boolean allIn;
    

    public AbsPlayer(String name, boolean human)
    {
        this.name = name;
        this.human = human;
        this.playingHand = false;
        this.cash = Player.START_BALANCE;
        this.hand = new Card[2]; 
        this.currentBet = 0;
        this.allIn = false;
    }
    
    /**
     * @return the currentBet
     */
    public int getCurrentBet()
    {
        return currentBet;
    }

    /**
     * @param currentBet the currentBet to set
     */
    public void setCurrentBet(int currentBet)
    {
        this.currentBet = currentBet;
    }

    public String getName()
    {
        return name;
    }

    public Card[] getHand()
    {
        // TODO Auto-generated method stub
        return hand;
    }
    
    @Override
    public int getBalance()
    {
        // TODO Auto-generated method stub
        return cash;
    }
    
    /**
     * If the player wins a hand the pot is passed
     * to this function
     */
    @Override
    public void addCash(int amount)
    {
        cash += amount;
    }
    
    @Override
    public Tup<WinningHands,Face> getBestHand()
    {
        return bestHand;
    }

    @Override
    public void setBestHand(Tup<WinningHands, Face> bestHand)
    {
        this.bestHand = bestHand;
    }
    
    @Override
    public void setHand(Card[] cards)
    {
        this.hand = cards;
    }

    /**
     * @return the allIn
     */
    @Override
    public boolean isAllIn()
    {
        return allIn;
    }

    /**
     * @param allIn the allIn to set
     */
    @Override
    public void setAllIn(boolean allIn)
    {
        this.allIn = allIn;
    }

    @Override
    public String toString()
    {
        String playerString = getName() +"\n";
        for (Card c : getHand())
        {
            if (c != null)
                playerString += c.toString() + "\n";
            else 
                playerString += "Hand empty\n";
        }
        return playerString;
    }
    
}
