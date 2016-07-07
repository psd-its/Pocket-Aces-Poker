package test_harness;

import java.util.Scanner;

import model.card.Card;
import model.card.TopHand;
import model.facade.AcesModel;
import model.game.Game;
import model.game.texas.TexasPoker;
import model.player.ComputerPlayer;
import model.player.HumanPlayer;
import model.player.Player;
import model.table.Table;
import model.table.TexasTable;
import view.main.MainView;

public class Harness
{
    private static AcesModel acesFacade;

    public Harness()
    {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Game poker = new TexasPoker();
        Table table;
        Scanner in = new Scanner(System.in);
        int result = -1;
        do
        {
            System.out.printf("-----------------------------\n");
            System.out.printf("%10s\n", "Tests Harness");
            System.out.printf("-----------------------------\n");
            System.out.printf("%-10d%s\n", 1, "Deal cards");
            System.out.printf("%-10d%s\n", 2, "Check random win conditions");
            System.out.printf("%-10d%s\n", 3, "Test the main view");
            System.out.printf("%-10d%s\n", 4, "Check all winning hands");
            System.out.printf("%-10d%s\n", 5, "Play Game");
            System.out.printf("%-10d%s\n", 0, "Exit");
            System.out.printf("\n%s:", "Enter selection");
            result = in.nextInt();

            switch (result)
            {
                case 1:
                {
                    table = new TexasTable();
                    poker.addTable(table);
                    System.out.println("preparing Players");
                    table.addPlayer(new ComputerPlayer("Taylor"));
                    table.addPlayer(new ComputerPlayer("Mathew"));
                    table.addPlayer(new HumanPlayer("Tristan"));
                    table.addPlayer(new HumanPlayer("Manuel"));
                    System.out.println("Players added");
                    table.dealCards();
                    System.out.println("cards dealt");
                    Card[] flop = table.getCardsInPlay();
                    Player[] players = table.getSeats();
                    for (int i = 0; i < players.length; ++i)
                    {
                        if (players[i] != null)
                        {
                            System.out.printf("%s\n", players[i].toString());
                        }
                    }
                    System.out.println("The shared cards are: \n");
                    for (Card c : flop)
                    {
                        System.out.printf("%s\n", c.toString());
                    }
                    break;
                }
                case 2:
                {
                    TopHand th = new TopHand();
                    // If there is no players add new
                    table = new TexasTable();
                    poker.addTable(table);

                    table.addPlayer(new ComputerPlayer("Taylor"));
                    table.addPlayer(new ComputerPlayer("Mathew"));
                    table.addPlayer(new HumanPlayer("Tristan"));
                    table.addPlayer(new HumanPlayer("Manuel"));

                    table.dealCards();

                    Player[] winner = null;

                    System.out.println("The winner('s) are: ");
                    try
                    {
                        winner = poker.checkForWinner();
                    }
                    catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (winner != null && winner.length > 0)
                    {
                        for (Player p : winner)
                        {
                            if (p != null)
                            {
                                System.out.printf("%s: %s high %s\n",
                                        p.getName(), p.getBestHand().l,
                                        p.getBestHand().f);

                            }
                        }
                    }
                    break;

                }

                case 3:
                    // setup facade
                    Harness.acesFacade = new AcesModel();
                    // setup main view
                    MainView mainView = new MainView(Harness.acesFacade);
                    mainView.setVisible(true);
                    break;
                case 4:
                    table = new TexasTable();
                    poker.addTable(table);
                    table.addPlayer(new ComputerPlayer("Taylor"));
                    table.addPlayer(new ComputerPlayer("Mathew"));
                    table.addPlayer(new HumanPlayer("Tristan"));
                    table.addPlayer(new HumanPlayer("Manuel"));
                    RecTest testAll = new RecTest(poker);
                    try
                    {
                        testAll.testAll(0);
                    }
                    catch (Exception e)
                    {
                        // If an exception is thrown there is a major error
                        // in game logic
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    int input = -1;

                    table = new TexasTable();
                    poker.addTable(table);
                    System.out.printf("Enter your name: ");
                    String name = in.nextLine();
                    Player p = new HumanPlayer(name);
                    table.addPlayer(p);
                    table.addPlayer(new ComputerPlayer("Taylor"));
                    table.addPlayer(new ComputerPlayer("Mathew"));
                    table.addPlayer(new ComputerPlayer("Tristan"));
                    table.addPlayer(new ComputerPlayer("Manuel"));
                    poker.play();
                    do
                    {
                        System.out.println("\nCommunity cards: ");
                        for (Card c : table.getCardsInPlay())
                        {
                            if (c.isShowing())
                            {
                                System.out.println(c.toString());
                            }
                        }
                        System.out.printf("Your cards: %s %s\n",
                                p.getHand()[0].toString(),
                                p.getHand()[1].toString());
                        System.out.printf("1. Fold 2. Check 3. Call 4. Raise"
                                + " 0. Fold\nEnter your selection:");
                        boolean done = false;
                        while (!done)
                        {
                            input = in.nextInt();
                            switch (input)
                            {
                                case 1:
                                    p.fold();
                                    done = true;
                                    break;
                                case 2:
                                    p.check(table);
                                    done = true;
                                    break;
                                case 3:
                                    if (p.call(table))
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        System.out.println("Call failed!");
                                    }
                                    break;
                                case 4:
                                    System.out
                                            .printf("Enter amount to raise: ");
                                    int amount = in.nextInt();
                                    if (p.raise(table, amount))
                                    {
                                        done = true;
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
                    } while (input > 0 && table.getSeats()[0].getBalance() > 0);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Enter the number corresponding"
                            + "to you selection");
                    break;

            }
        } while (result != 0);

    }
}
