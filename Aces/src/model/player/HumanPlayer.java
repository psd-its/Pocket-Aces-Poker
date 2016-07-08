package model.player;

import model.card.Card;
import model.game.Game;
import test_harness.Harness;

public class HumanPlayer extends AbsPlayer
{

    // private int currentBet;

    public HumanPlayer(String name)
    {
        super(name);
    }

    @Override
    public void playHand(Game g)
    {
        Options input;
        // Timer t = g.getTimer();
        // t.schedule(this, 30000);

        System.out.println("\nCommunity cards: ");
        for (Card c : g.getTable().getCardsInPlay())
        {
            if (c.isShowing())
            {
                System.out.println(c.toString());
            }
        }
        System.out.printf("Your cards: %s %s\n", hand[0].toString(),
                hand[1].toString());
        System.out.printf("1. Fold 2. Check 3. Call 4. Raise"
                + " 0. Fold\nEnter your selection:");
        boolean done = false;
        while (!done)
        {
            input = Options.values()[Harness.in.nextInt()];
            // Clear trailing new line char
            Harness.in.nextLine();
            switch (input)
            {
                case FOLD:
                    fold();
                    done = true;
                    System.out.println("you folded!");
                    // g.getTimer().cancel();
                    break;
                case CHECK:
                    check(g.getTable());
                    done = true;
                    System.out.println("you checked!");
                    // g.getTimer().cancel();
                    break;
                case CALL:
                    if (call(g.getTable()))
                    {
                        done = true;
                        System.out.println("you called!");
                        return;
                        // g.getTimer().cancel();
                    }
                    else
                    {
                        System.out.println("Call failed!");
                    }
                    break;
                case RAISE:
                    System.out.printf("Enter amount to raise: ");
                    int amount = Harness.in.nextInt();
                    if (raise(g.getTable(), amount))
                    {
                        done = true;
                        System.out.println("you raised the pot by " + amount);
                        return;
                        // g.getTimer().cancel();
                    }
                    else
                    {
                        System.out.println("Raise failed!");
                    }
                    break;
                case POT:
                    System.out.println("Current pot is: " + g.getTable().getPot());
                    break;
                case MY_CASH:
                    System.out.println("Current cash remaining: " + cash);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
        }

    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        this.fold();
        System.out.println("You have been folded");

    }

}
