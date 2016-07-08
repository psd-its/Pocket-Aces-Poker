package model.player;

import java.util.ArrayList;
import java.util.List;

import model.Rand;
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
    public void playHand(Game g)
    {
//        // 
//        List<Card> cards = 
//                new ArrayList<Card>();
//        // get AI cards
//        for(Card c : hand)
//        {
//            cards.add(c);
//        }
//        // get communal cards showing
//        for (Card c : g.getTable().getCardsInPlay())
//        {
//            if(c.isShowing())
//            {
//                cards.add(c);
//            }
//        }
//        bestHand = g.getTh().processHand(cards.toArray(new 
//                Card[cards.size()])).get(0);
//        if (g.getTable().getCurrentBet() < g.getTable().getBlind() * 4 &&
//                cards.size() < 6)
//        {
//            call(g.getTable());
//            System.out.println(name + " calls"); 
//            return;
//        }
//        else if (cards.size() > 6 && 
//                bestHand.f.ordinal() > WinningHands.HIGH_CARD.ordinal())
//        {
//            if (g.getTable().getCurrentBet() > g.getTable().getBlind() * 5)
//            {
//                call(g.getTable());
//                System.out.println(name + " calls"); 
//                return;
//            }
//            else 
//            {
//                int bet = ((cash * 10)/ 100);
//                raise(g.getTable(), bet);
//                System.out.println(name + " raises by " + bet); 
//                return;
//            }
//        }
        int choice = Rand.getRand().nextInt(3);
        switch(Options.values()[choice])
        {
            case CALL:
                super.call(g.getTable());
                System.out.println(name + " calls"); 
                break;
            case FOLD:
                super.fold();
                System.out.println(name + " folds"); 
                break;
            case RAISE:
                int bet = cash / 10;
                super.raise(g.getTable(), bet);
                System.out.println(name + " raises by " + bet); 
                break;
            default:
                break;
            
        }
        
        
    }

    @Override
    public void run()
    {
        // this is not needed for PC player 
        
    }

    
    
}
