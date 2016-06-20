package model.player;

import model.card.Card;

public class HumanPlayer extends AbsPlayer
{

    public HumanPlayer(String name)
    {
        super(name, true);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean placeBet(int bet)
    {
        // TODO Auto-generated method stub
        return false;
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
