package model.player;

import java.util.InputMismatchException;

import model.card.Card;
import model.game.Game;
import model.game.RTM;
import model.game.texas.Const;
import test_harness.Harness;

public class HumanPlayer extends AbsPlayer
{

    // Currently set for console play;

    public HumanPlayer(String name)
    {
        super(name);
    }

    @Override
    public void playHand(Game g) throws RTM
    {
        // initialize var to null
        Options input = null;
        int tableBet = 0;
        String tableUpdate = null;
        // Timer t = g.getTimer();
        // t.schedule(this, 30000);

        // print communal cards
        System.out.println("\nCommunity cards: ");
        for (Card c : g.getTable().getCardsInPlay())
        {
            if (c.isShowing())
            {
                System.out.println(c.toString());
            }
        }
        // print players cards
        System.out.printf("Your cards:\n%s\n%s\n\n", hand[0].toString(),
                hand[1].toString());
        // Update the player of the current min bet required to play
        tableBet = g.getTable().getCurrentBet() - currentBet;
        if (tableBet > 0)
        {
            tableUpdate = "$" + tableBet + " to call";
        }
        else
        {
            tableUpdate = "You can check or raise";
        }
        System.out.println(tableUpdate);
        // menu loop
        boolean done = false;
        while (!done)
        {
            // print options
            System.out.printf("0. Call \n1. Raise  \n2. Fold \n3. Check"
                    + "\n4. Show Pot \n5. Show remaining cash"
                    + "\n6. Return to main menu \nEnter your selection: ");
            int selection = -1;
            // get users input
            while (selection < 0 || selection >= Const.MENU_ITEMS)
            {

                try
                {
                    selection = Harness.in.nextInt();
                    Harness.in.nextLine();
                    if (selection >= 0 && selection < Const.MENU_ITEMS)
                    {
                        input = Options.values()[selection];

                    }
                    else
                    {
                        System.out.println("Enter selection: ");
                        continue;
                    }
                }
                // ensure valid input
                catch (InputMismatchException ex)
                {
                    System.out.println("ERROR- please enter a valid integer"
                            + " in the specified range\nEnter selection: ");
                    Harness.in.nextLine();

                }
            }
            // Clear trailing new line char
            // Harness.in.nextLine();
            switch (input)
            {
                case FOLD:
                    fold();
                    done = true;
                    System.out.println("you folded!");
                    // g.getTimer().cancel();
                    break;
                case CHECK:
                    if (check(g.getTable()))
                    {
                        done = true;
                        System.out.println("you checked!");
                    }
                    else
                    {
                        System.out.println("Check failed");
                    }
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
                    System.out.println("Current pot is: "
                            + g.getTable().getPot());
                    break;
                case MY_CASH:
                    System.out.println("Current cash remaining: " + cash);
                    break;
                case EXIT:
                    throw new RTM();
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
