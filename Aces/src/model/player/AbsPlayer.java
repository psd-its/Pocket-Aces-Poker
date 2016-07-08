package model.player;

import java.util.TimerTask;

import model.card.Card;
import model.card.Face;
import model.card.Tup;
import model.card.WinningHands;
import model.table.Table;

public abstract class AbsPlayer extends TimerTask implements Player
{
    // This holds the amount the player has staked
    // in the current round of betting. It is moved into the
    // pot before the next round of betting starts
    protected int currentBet;
    // players name
    protected String name;
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

    public AbsPlayer(String name)
    {
        this.name = name;
        this.playingHand = true;
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
     * @param currentBet
     *            the currentBet to set
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

    /**
     * If the player wins a hand the pot is passed to this function
     */
    @Override
    public void addCash(int amount)
    {
        cash += amount;
    }

    @Override
    public Tup<WinningHands, Face> getBestHand()
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
     * @param allIn
     *            the allIn to set
     */
    @Override
    public void setAllIn(boolean allIn)
    {
        this.allIn = allIn;
    }

    @Override
    public String toString()
    {
        String playerString = getName() + "\n";
        for (Card c : getHand())
        {
            if (c != null)
                playerString += c.toString() + "\n";
            else
                playerString += "Hand empty\n";
        }
        return playerString;
    }

    @Override
    public void allIn(int cash)
    {
        placeBet(cash);
        this.allIn = true;
    }

    @Override
    public int getBalance()
    {
        return cash;
    }

    @Override
    public boolean fold()
    {
        if (!playingHand) return false;
        playingHand = false;
        return true;
    }

    public void setPlayingHand(boolean playingHand)
    {
        this.playingHand = playingHand;
    }

    @Override
    public boolean isPlaying()
    {
        return this.playingHand;
    }

    @Override
    public boolean placeBet(int bet)
    {
        if (bet <= cash)
        {
            cash -= bet;
            return true;
        }

        return false;

    }

    @Override
    public boolean check(Table t)
    {
        if (t.getCurrentBet() == currentBet)
        {
            return true;
        }
        // Ends turn
        return false;
    }

    @Override
    public boolean call(Table t)
    {
        // get the difference between the the current expected
        // bet and what you have already put in this round of betting
        int bet = t.getCurrentBet() - currentBet;
        if (bet <= cash)
        {
            cash = (cash - bet);
            currentBet += bet;
            t.addToPot(bet);
            return true;
        }

        return false;

    }

    @Override
    public boolean raise(Table t, int amount)
    {
        if (amount <= cash)
        {
            if (t.getCurrentBet() > currentBet + amount)
                return false;
            currentBet += amount;
            placeBet(amount);
            t.setCurrentBet(currentBet);
            return true;
        }
        return false;

    }

}
