package model.player;

import model.game.Game;
import model.table.Table;

public class HumanPlayer extends AbsPlayer 
{

    private int currentBet;

    public HumanPlayer(String name)
    {
        super(name);
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
        if(t.getCurrentBet() == currentBet)
        {
            return true;
        }
        //Ends turn
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
            return true;
        }
       
        return false;
        
    }

   @Override
    public boolean raise(Table t, int amount)
    {
        if ((amount <= cash) && (amount > t.getCurrentBet()))
        {
            currentBet += amount;
            placeBet(amount);
            // This is done in place bet 
            // cash = cash - amount;
            return true;
        }
        return false;
        
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
        if (!playingHand)
            return false;
        playingHand = false;
        return true;
    }
    
    @Override
    public boolean isPlaying()
    {
        return this.playingHand;
    }

    @Override
    public void playHand(Game g)
    {
        // TODO Auto-generated method stub
        try
        {
            g.getThread().wait(30000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

   

    

    

}
