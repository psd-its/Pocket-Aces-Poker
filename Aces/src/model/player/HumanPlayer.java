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
    public boolean placeBet(int bet)
    {
        if (bet <= cash)
        {
            cash = (cash - bet);
            return true;
        }
        else
        {
            return false;
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
            return true;
        }
        else
        {
        return false;
        }
    }

   @Override
    public boolean raise(int amount)
    {
        if ((amount <= cash) && (amount > currentBet))
        {
            amount = (currentBet + amount);
            placeBet(amount);
            cash = cash - amount;
            return true;
        }
        else
        {
        return false;
        }
    }

    @Override
    public void allIn(int cash)
    {
        placeBet(cash);
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
        return this.playingHand;
    }

}
