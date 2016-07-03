package model.player;

import model.game.Game;
import model.table.Table;

public class ComputerPlayer extends AbsPlayer 
{

    public ComputerPlayer(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean placeBet(int bet)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean check(Table t)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean call(Table t)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean raise(Table t, int ammount)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void allIn(int cash)
    {
        // TODO Auto-generated method stub
        
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

    @Override
    public void playHand(Game g)
    {
        // TODO Auto-generated method stub
        
    }

    
}
