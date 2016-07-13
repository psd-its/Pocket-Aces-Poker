package test_harness;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.card.Card;
import model.card.TopHand;
import model.facade.AcesModel;
import model.game.Game;
import model.game.RTM;
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
    // Made the scanner a global var as for console version 
    // its also accessed inside human player
    public static Scanner in = new Scanner(System.in);

    public Harness()
    {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Game poker = new TexasPoker();
        Table table;

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
            try
            {
                result = in.nextInt();
                // Clear trailing new line char
                in.nextLine();
            }
            catch(InputMismatchException ex)
            {
                result = -1;
                // Clear trailing new line char
                in.nextLine();
            }
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

                    // Player[] winner = null;

                    try
                    {
                        System.out.println("The winner('s) are: ");
                        Player[] winner = poker.checkForWinner();
                        for (Player p : winner)
                        {
//                            System.out.println(p);
//                            System.out.printf("%s: %s high %s\n", p.getName(),
//                                    p.getBestHand().l, p.getBestHand().f);

                        }
                    }
                    catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
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
                    try
                    {
                        poker.play();
                    }
                    catch(RTM ex)
                    {
                        break;
                    }
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
