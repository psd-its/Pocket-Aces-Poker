package model.game.texas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.card.Process;
import model.card.*;
import model.game.Game;
import model.player.Player;
import model.table.Table;
import model.table.TableFull;

/**
 * 
 * @author Tristan s3528615
 * 
 */
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
    public Player[] checkForWinner() throws Exception
    {
        // declare data structures
        // List<Tup<List<Tup<WinningHands, Face>>, Player>> playList =
        // new ArrayList<Tup<List<Tup<WinningHands, Face>>, Player>>();
        Card[] cards = new Card[7];
        // im using System.arraycopy instead of a loop to get community cards
        System.arraycopy(table.getCardsInPlay(), 0, cards, 0, 5);
        // get players
        // Add the players cards to the array containing the shared cards
        for (Player p : table.getSeats())
        {
            if (p != null)
            {
                cards[Process.HOLE_L] = p.getHand()[0];
                cards[Process.HOLE_R] = p.getHand()[1];

                // Tup<List<Tup<WinningHands, Face>>, Player> best =
                // new Tup<List<Tup<WinningHands, Face>>, Player>(th
                // .processHand(cards), p);
                // playList.add(best);
                // System.out.println(best);
                // //p.setBestHand(best.f.get(0));
                for(Card c : cards)
                {
                    if (c != null)
                        System.out.println("card: " + c.toString());
                    else
                        System.out.println("card: NULL ?!?");
                        
                }
                List<Tup<WinningHands, Face>> best = th.processHand(cards);
                if (best.isEmpty())
                {
                    throw new Exception("Error! - processHand has not returned a result");
                }
                p.setBestHand(best.get(0));
            }
        }
        int index = 0;
        List<Integer> indexes = new ArrayList<Integer>();
        Tup<WinningHands, Face> bestSoFar = null;
        Face f1 = null, f2 = null;
        for (Player h : table.getSeats())
        {
            if (h == null)
                break;
            System.out.println(index);
            if (bestSoFar == null)
            {
                // Add first card as best so far
                bestSoFar = new Tup<WinningHands, Face>(h.getBestHand().f,
                        h.getBestHand().l);
                // h.f.get(index).f;
                // bestSoFar.l = h.f.get(index).l;
                indexes.add(index);
            }
            // New best so far
            else if (bestSoFar.f.ordinal() < h.getBestHand().f.ordinal())
            {
                bestSoFar = new Tup<WinningHands, Face>(h.getBestHand().f,
                        h.getBestHand().l);
                indexes.clear();
                indexes.add(index);
            }
            // Need to break tie
            else if (bestSoFar.f.ordinal() == h.getBestHand().f.ordinal())
            {
                // Card value breaks tie no need to reorder
                if (bestSoFar.l.ordinal() > h.getBestHand().l.ordinal())
                    continue;
                // Card value breaks tie need to reorder
                else if (bestSoFar.l.ordinal() < h.getBestHand().l.ordinal())
                {
                    bestSoFar = new Tup<WinningHands, Face>(h.getBestHand().f,
                            h.getBestHand().l);
                    indexes.clear();
                    indexes.add(index);
                }
                // Card value cannot break tie
                else if (bestSoFar.l.equals(h.getBestHand().l))
                {
                    // Break tie or split pot
                    switch (bestSoFar.f)
                    {
                    // Matching high cards so split pot
                        case FLUSH:
                            indexes.add(index);
                            break;
                        // This can never occur
                        case FOUR_OF_A_KIND:
                            throw new Exception("Error there can't be 8"
                                    + " cards of the same value");
                            // Highest Three of a kind wins if they match pair
                            // breaks the tie
                        case FULL_HOUSE:
                            int pos = 0;
                            Face best2 = null;
                            for (int i = 0; i < 2; ++i)
                            {
                                switch(i)
                                {
                                    case 0:
                                        cards[Process.HOLE_L] = h.getHand()[0];
                                        cards[Process.HOLE_R] = h.getHand()[1];
                                        break;
                                    case 1:
                                        h = table.getSeats()[indexes.get(0)];
                                        cards[Process.HOLE_L] = h.getHand()[0];
                                        cards[Process.HOLE_R] = h.getHand()[1];
                                        break;
                                }
                                // Compare the pairs in the full house
                                for (Tup<Face, Integer> tl : th
                                        .getMultiples(cards))
                                {
                                    // only examine if its a pair
                                    if (tl.l == 2)
                                    {

                                        if (best2 == null)
                                        {
                                            best2 = tl.f;
                                            pos = index;
                                        }
                                        // original full house wins
                                        else if (best2.ordinal() < tl.f
                                                .ordinal())
                                            continue;
                                        // new winner
                                        else if (best2.ordinal() > tl.f
                                                .ordinal())
                                        {
                                            // update the seat index of the 
                                            // winner
                                            best2 = tl.f;
                                            indexes.clear();
                                            indexes.add(pos);
                                        }
                                        else if (best2.ordinal() == tl.f
                                                .ordinal())
                                        {
                                            // Add a second winner (split
                                            // pot)
                                            indexes.add(pos);
                                        }

                                    }
                                    
                                }
                            }
                            break;
                        // Highest card wins (bluff break)
                        case HIGH_CARD:
                            // Vars needed for ease of comparison
                            Player p1,
                            p2;
                            List<Card> hand1 = new ArrayList<Card>(
                                    Arrays.asList(table.getCardsInPlay()));
                            List<Card> hand2 = new ArrayList<Card>(
                                    Arrays.asList(table.getCardsInPlay()));
                            // add the players cards
                            p1 = table.getSeats()[indexes.get(0)];
                            p2 = table.getSeats()[index];
                            hand1.add(p1.getHand()[0]);
                            hand1.add(p1.getHand()[1]);
                            hand2.add(p2.getHand()[0]);
                            hand2.add(p2.getHand()[1]);
                            
                            boolean won = false;
                            // Loop through 5 best cards unless tie is broken
                            for (int i = 0; i < 5; ++i)
                            {
                                f1 = th.highCard(hand1.toArray(new Card[hand1.size()]));
                                f2 = th.highCard(hand2.toArray(new Card[hand2.size()]));
                                // cards match remove them and
                                if (f1 == f2)
                                {
                                    hand1.remove(f1);
                                    hand2.remove(f2);
                                }
                                // tie broken update winner
                                else if (f1.ordinal() < f2.ordinal())
                                {
                                    indexes.clear();
                                    indexes.add(index);
                                    won = true;
                                    break;
                                }
                                // tie broken no need to update
                                else
                                {
                                    won = true;
                                    break;
                                }
                            }
                            // split pot
                            if (!won)
                            {
                                indexes.add(index);
                            }
                            break;
                        // Highest pair wins, if pairs match then high card wins
                        case PAIR:

                            hand1 = new ArrayList<Card>(Arrays.asList(table
                                    .getCardsInPlay()));
                            hand2 = new ArrayList<Card>(Arrays.asList(table
                                    .getCardsInPlay()));
                            // add the players and there cards
                            p1 = table.getSeats()[indexes.get(0)];
                            p2 = table.getSeats()[index];
                            hand1.add(p1.getHand()[0]);
                            hand1.add(p1.getHand()[1]);
                            hand2.add(p2.getHand()[0]);
                            hand2.add(p2.getHand()[1]);
                            Card c;
                            // Remove the pair from the hand
                            for (Iterator<Card> it = hand1.iterator(); 
                                    it.hasNext();)
                            {
                                c = it.next();
                                if (c.getValue() == bestSoFar.l)
                                {
                                    it.remove();
                                }
                            }
                            for (Iterator<Card> it = hand2.iterator(); 
                                    it.hasNext();)
                            {
                                c = it.next();
                                if (c.getValue() == bestSoFar.l)
                                {
                                    it.remove();
                                }
                            }
                            // Place holder
                            won = false;
                            // Compare the rest of the cards looking for high card
                            for (int i = 0; i < 3; ++i)
                            {
                                f1 = th.highCard(hand1.toArray(new Card[hand1.size()]));
                                f2 = th.highCard(hand2.toArray(new Card[hand2.size()]));
                                // cards match remove them and
                                if (f1 == f2)
                                {
                                    hand1.remove(f1);
                                    hand2.remove(f2);
                                }
                                // tie broken update winner
                                else if (f1.ordinal() < f2.ordinal())
                                {
                                    indexes.clear();
                                    indexes.add(index);
                                    won = true;
                                    break;
                                }
                                // tie broken no need to update
                                else
                                {
                                    won = true;
                                    break;
                                }
                            }
                            // split pot
                            if (!won)
                            {
                                indexes.add(index);
                            }
                            break;
                        // For there to be multiple hands with
                        // royal flush it has to be a split pot
                        case ROYAL_FLUSH:
                            indexes.add(index);
                            break;
                        // Matching straight high card means split pot
                        case STRAIGHT:
                            indexes.add(index);
                            break;
                        // Matching straight high card means split pot
                        case STRAIGHT_FLUSH:
                            indexes.add(index);
                            break;
                        // Highest value card wins so should never make it here
                        case THREE_OF_A_KIND:
                            throw new Exception("Error there can't be 6"
                                    + " cards of the same value");
                            // If both pairs match high card wins.
                            // if high card matches then split pot
                        case TWO_PAIR:
                            hand1 = new ArrayList<Card>(Arrays.asList(table
                                    .getCardsInPlay()));
                            hand2 = new ArrayList<Card>(Arrays.asList(table
                                    .getCardsInPlay()));
                            // add the players and there cards
                            p1 = table.getSeats()[indexes.get(0)];
                            p2 = table.getSeats()[index];
                            hand1.add(p1.getHand()[0]);
                            hand1.add(p1.getHand()[1]);
                            hand2.add(p2.getHand()[0]);
                            hand2.add(p2.getHand()[1]);
                            // Vars for ease of comparison
                            Face p1_pair1, p1_pair2, p2_pair1, p2_pair2;
                            p1_pair1 = p1_pair2 = p2_pair1 = p2_pair2 = null;
                            List<Tup<Face, Integer>> p1_multiples, p2_multiples;
                            p1_multiples = th.getMultiples(hand1.toArray(new Card[hand1.size()]));
                            p2_multiples = th.getMultiples(hand2.toArray(new Card[hand2.size()]));
                            // both hands on have 2 pairs
                            if(p1_multiples.size() == 2 &&
                                    p2_multiples.size() ==2)
                            {
                                p1_pair1 = p1_multiples.get(0).f;
                                p1_pair2 = p1_multiples.get(1).f;
                                p2_pair1 = p2_multiples.get(0).f;
                                p2_pair2 = p2_multiples.get(1).f;                    
                                
                            }
                            // Hands have more the 2 pairs need to determine
                            // the highest pairs for each hand
                            else 
                            {
                                if (p1_multiples.size() > 2)
                                {
                                    p1_pair1 = p1_multiples.get(0).f;
                                    p1_pair2 = p1_multiples.get(1).f;
                                    Face tmp = p1_multiples.get(2).f;
                                    if (tmp.ordinal() > p1_pair1.ordinal())
                                    {
                                        if (tmp.ordinal() > p1_pair2.ordinal())
                                        {
                                            if(p1_pair1.ordinal() > p1_pair2.ordinal())
                                            {
                                                p1_pair2 = tmp;
                                            }
                                            else
                                            {
                                                p1_pair1 = tmp;
                                            }
                                        }
                                        else
                                        {
                                            p1_pair1 = tmp;
                                        }
                                    }
                                    else
                                    {
                                        // if we do not enter this if then 
                                        // tmp is the lowest pair
                                        if (tmp.ordinal() > p1_pair2.ordinal())
                                        {
                                            p1_pair2 = tmp;
                                        }
                                    }
                                }
                            }
                            // 1 pair matches
                            if(p1_pair1 == p2_pair1 || p1_pair1 == p2_pair2)
                            {
                                // both pairs match
                                if (p1_pair2 == p2_pair1 || p1_pair2 == p2_pair2)
                                {
                                    // remove pairs from the 
                                    for (Iterator<Card> it = hand1.iterator(); 
                                            it.hasNext();)
                                    {
                                        c = it.next();
                                        if (c.getValue() == p1_pair1
                                                || c.getValue() == p1_pair2)
                                        {
                                            it.remove();
                                        }
                                    }
                                    for (Iterator<Card> it = hand2.iterator(); 
                                            it.hasNext();)
                                    {
                                        c = it.next();
                                        if (c.getValue() == p2_pair1
                                                || c.getValue() == p2_pair2)
                                        {
                                            it.remove();
                                        }
                                    }
                                    f1 = th.highCard(hand1.toArray(new Card[hand1.size()]));
                                    f2 = th.highCard(hand2.toArray(new Card[hand2.size()]));
                                    
                                }
                                // if pairs are different assign the values
                                // to a var for comparison
                                else if (p1_pair1 == p2_pair1)
                                {
                                    f1 = p1_pair2;
                                    f2 = p2_pair2;
                                }
                                else
                                {
                                    f1 = p1_pair2;
                                    f2 = p2_pair1;
                                }
                            }
                            if (f1 == f2 && f1 != null)
                            {
                                indexes.add(index);
                            }
                            else if (f1.ordinal() < f2.ordinal())
                            {
                                indexes.clear();
                                indexes.add(index);
                            }
                            break;
                            
                            
                        default:
                            break;

                    }
                }

            }
            ++index;
        }
        Player[] players = new Player[indexes.size()];
        for (int i = 0; i < indexes.size(); ++i)
        {
            players[i] = table.getSeats()[i];
        }
        return players;
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
