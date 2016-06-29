package model.player;

import model.card.Card;
import model.card.WinningHands;
import model.table.Table;

public class ComputerPlayer extends AbsPlayer
{

    public ComputerPlayer(String name)
    {
        super(name, false);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void placeBet(int bet)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean check(Table t)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean call(int bet)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void raise(int ammount)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean allIn(int cash)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean fold()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isPlaying()
    {
        // TODO Auto-generated method stub
        return false;
    }

    
   
    
}
