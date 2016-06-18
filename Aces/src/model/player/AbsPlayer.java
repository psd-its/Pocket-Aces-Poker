package model.player;

import java.util.Arrays;

import model.card.Card;

public abstract class AbsPlayer implements Player
{
    protected String name;
    protected boolean human;
    protected boolean playingHand;
    protected int cash;
    protected Card hand[];
    public AbsPlayer(String name, boolean Human)
    {
        this.name = name;
        this.human = human;
        this.playingHand = false;
        this.cash = Player.START_BALANCE;
        this.hand = new Card[2]; 
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
