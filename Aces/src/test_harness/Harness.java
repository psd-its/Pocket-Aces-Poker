package test_harness;

import model.card.Card;
import model.player.ComputerPlayer;
import model.player.HumanPlayer;
import model.player.Player;
import model.table.Table;
import model.table.TexasTable;

import view.main.*;

public class Harness
{

    public Harness()
    {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Table table = new TexasTable();
        System.out.println("preparing Players");
        table.addPlayer(new ComputerPlayer("Marty"));
        table.addPlayer(new ComputerPlayer("Matthew"));
        table.addPlayer(new HumanPlayer("Tristan"));
        table.addPlayer(new HumanPlayer("Manuel"));
        System.out.println("Players added");
        table.dealCards();
        System.out.println("cards dealt");
        Card[] flop = table.getCardsInPlay();
        Player[] players = table.getSeats();
        for (int i = 0; i < players.length; ++i)
        {
            if(players[i] != null)
            {    
                System.out.printf("%s\n",players[i].toString());
            }
        }
        System.out.println("The shared cards are: \n");
        for (Card c : flop)
        {
            System.out.printf("%s\n", c.toString());
        }
        
        MainView mainView = new MainView();
        mainView.setVisible(true);

    }

}
