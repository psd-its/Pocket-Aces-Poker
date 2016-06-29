package model.player;

import model.table.Table;

public class HumanPlayer extends AbsPlayer
{

    private int currentBet;

    public HumanPlayer(String name)
    {
        super(name, true);
    }
    
    @Override
    public void placeBet(int bet)
    {
        if (bet <= cash)
        {
            cash = (cash - bet);
            // Playing hand indicates that a player is still in the
            // round. Sorry should of been clearer
            //playingHand = false;
        }
    }

    @Override
    public boolean check(Table t)
    {
        //Ends turn
        return false;
    }

    @Override
    public boolean call(int bet)
    {
        if (bet <= cash)
        {
            cash = (cash - bet);
            //playingHand = false;
            return true;
        }
        return false;
    }

   @Override
    public void raise(int amount)
    {
        if (amount <= cash && (amount > currentBet))
        {
            amount = (currentBet + amount);
            placeBet(amount);
            cash = cash - amount;
            playingHand = false;
        }
    }

    @Override
    public boolean allIn(int cash)
    {
        placeBet(cash);
        return false;
    }

    @Override
    public int getBalance()
    {
        return cash;
    }

    @Override
    public boolean fold()
    {
        return false;
    }

    @Override
    public boolean isPlaying()
    {
        return false;
    }

}
