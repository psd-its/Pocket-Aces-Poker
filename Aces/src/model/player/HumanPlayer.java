package model.player;

import java.util.Timer;

import test_harness.Harness;
import model.card.Card;
import model.game.Game;
import model.table.Table;

public class HumanPlayer extends AbsPlayer 
{

    //private int currentBet;

    public HumanPlayer(String name)
    {
        super(name);
    }
    
    
    
    @Override
    public void playHand(Game g)
    {
        int input = -1;
//        Timer t = g.getTimer();  
//        t.schedule(this, 30000);
        do
        {
            System.out.println("\nCommunity cards: ");
            for (Card c : g.getTable().getCardsInPlay())
            {
                if (c.isShowing())
                {
                    System.out.println(c.toString());
                }
            }
            System.out.printf("Your cards: %s %s\n",
                    hand[0].toString(),
                    hand[1].toString());
            System.out.printf("1. Fold 2. Check 3. Call 4. Raise"
                    + " 0. Fold\nEnter your selection:");
            boolean done = false;
            while (!done)
            {
                input = Harness.in.nextInt();
                // Clear trailing new line char
                Harness.in.nextLine();
                switch (input)
                {
                    case 1:
                        fold();
                        done = true;
                        System.out.println("you folded!");
                        //g.getTimer().cancel();
                        break;
                    case 2:
                        check(g.getTable());
                        done = true;
                        System.out.println("you checked!");
                        //g.getTimer().cancel();
                        break;
                    case 3:
                        if (call(g.getTable()))
                        {
                            done = true;
                            System.out.println("you called!");
                            //g.getTimer().cancel();
                        }
                        else
                        {
                            System.out.println("Call failed!");
                        }
                        break;
                    case 4:
                        System.out
                                .printf("Enter amount to raise: ");
                        int amount = Harness.in.nextInt();
                        if (raise(g.getTable(), amount))
                        {
                            done = true;
                            System.out.println("you raised the pot by " + amount);
                            //g.getTimer().cancel();
                        }
                        else
                        {
                            System.out.println("Raise failed!");
                        }
                        break;
                    case 0:
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;

                }
            }
        } while (input > 0 && g.getTable().getSeats()[0].getBalance() > 0);
        
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        this.fold();
        System.out.println("You have been folded");
        
    }

   

    

    

}
