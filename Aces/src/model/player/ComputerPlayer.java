package model.player;

import java.util.ArrayList;
import java.util.List;

import model.card.Card;
import model.card.Process;
import model.card.WinningHands;
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
        // 
        List<Card> cards = 
                new ArrayList<Card>();
        // get AI cards
        for(Card c : hand)
        {
            cards.add(c);
        }
        // get communal cards showing
        for (Card c : g.getTable().getCardsInPlay())
        {
            if(c.isShowing())
            {
                cards.add(c);
            }
        }
        bestHand = g.getTh().processHand(cards.toArray(new 
                Card[cards.size()])).get(0);
        if (g.getTable().getCurrentBet() < g.getTable().getBlind() * 4 &&
                cards.size() < 6)
        {
            call(g.getTable());
            return;
        }
        else if (cards.size() > 6 && 
                bestHand.f.ordinal() > WinningHands.HIGH_CARD.ordinal())
        {
            if (g.getTable().getCurrentBet() > g.getTable().getBlind() * 5)
            {
                call(g.getTable());
                return;
            }
            else 
            {
                int bet = ((cash * 10)/ 100);
                raise(g.getTable(), bet);
                return;
            }
        }
        
        
    }

    
    
}
