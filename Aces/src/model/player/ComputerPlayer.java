package model.player;

import model.card.Card;
import model.card.WinningHands;

public class ComputerPlayer extends AbsPlayer
{

    public ComputerPlayer(String name)
    {
        super(name, false);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean placeBet(int bet)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getBalance()
    {
        // TODO Auto-generated method stub
        return cash;
    }

    @Override
    public boolean check()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean call()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean raise(int ammount)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean allIn()
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
