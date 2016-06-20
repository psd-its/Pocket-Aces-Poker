package model.game.texas;

import java.util.ArrayList;
import java.util.List;

import model.card.Card;
import model.game.Game;
import model.player.Player;
import model.table.Table;
import model.table.TableFull;

public class TexasPoker implements Game
{
    private Table table;
    public TexasPoker()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addTable(Table table)
    {
        // Table object stored as an instance var
        this.table = table;
    }

    @Override
    public void addPlayer(Player player) throws TableFull
    {
        Player[] seats = table.getSeats();
        // step through until we find an empty seat
        for (int i = 0; i < seats.length; ++i)
        {
            // empty seat found
            if (seats[i] == null)
            {
                //add the player to the seat and exit
                seats[i] = player;
                return;
            }
        }
        // If we are here no seat has been found
        throw new TableFull("Error! The table is full");       
    }

    @Override
    public void dealCards()
    {
        // deal cards
        table.dealCards();

    }

    @Override
    public Player[] checkForWinner()
    {
        // declare data structure to hold winner(s)
        List list = new ArrayList();
        Card[] cardsUsed = new Card[5];
        return null;
    }

    @Override
    public void takeTurn(Player player)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Player[] getPlayers()
    {
        // TODO Auto-generated method stub
        return table.getSeats();
    }

    @Override
    public Table getTable()
    {
        // TODO Auto-generated method stub
        return this.table;
    }

    @Override
    public void play()
    {
        // TODO Auto-generated method stub
        
    }
}
