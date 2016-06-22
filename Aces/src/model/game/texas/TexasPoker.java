package model.game.texas;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.Face;
import model.card.WinningHands;
import model.card.Process;
import model.card.*;
import model.game.Game;
import model.player.Player;
import model.table.Table;
import model.table.TableFull;

public class TexasPoker implements Game
{
    private TopHand th;
    private Table table;

    public TexasPoker()
    {
        // TODO Auto-generated constructor stub
        this.th = new TopHand();
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
                // add the player to the seat and exit
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
        // declare data structures
        List<Tup<List<Tup<WinningHands, Face>>, Player>> playList = 
                new ArrayList<Tup<List<Tup<WinningHands, Face>>, Player>>();
        Card[] cards = new Card[7];
        // im using System.arraycopy instead of a loop to get community cards
        System.arraycopy(table.getCardsInPlay(), 0, cards, 0, 5);
        // get players
        for (Player p : table.getSeats())
        {
            if (p != null)
            {
                cards[Process.HOLE_L] = p.getHand()[0];
                cards[Process.HOLE_R] = p.getHand()[1];
                playList.add(new Tup<List<Tup<WinningHands, Face>>, Player>(th
                        .processHand(cards), p));
            }
        }
        int index = 0;
        List<Integer> indexes = new ArrayList<Integer>();
        Tup<WinningHands, Face> bestSoFar = null;
        for(Tup<List<Tup<WinningHands, Face>>, Player> h: playList)
        {
            if (bestSoFar == null)
            {
                // Add first card as best so far
                bestSoFar.f = h.f.get(index).f;
                bestSoFar.l = h.f.get(index).l;
                indexes.add(0, index);                       
            }
            else if(bestSoFar.f.ordinal() < h.f.get(index).f.ordinal())
            {
                bestSoFar.f = h.f.get(index).f;
                bestSoFar.l = h.f.get(index).l;
                indexes.add(0, index);    
            }
            else if (bestSoFar.f.ordinal() == h.f.get(index).f.ordinal())
            {
                if (bestSoFar.l.ordinal() > h.f.get(index).l.ordinal()) continue;
                else if (bestSoFar.l.ordinal() < h.f.get(index).l.ordinal())
                {
                    bestSoFar.f = h.f.get(index).f;
                    bestSoFar.l = h.f.get(index).l;
                    indexes.add(0, index);    
                }
            }
            ++index;
        }
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
