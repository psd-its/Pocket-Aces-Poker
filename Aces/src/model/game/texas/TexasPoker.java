package model.game.texas;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Tup<List<Tup<WinningHands, Face>>, Player>> playList = new ArrayList<Tup<List<Tup<WinningHands, Face>>, Player>>();
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
                playList.add(new Tup<List<Tup<WinningHands, Face>>, Player>(th
                        .processHand(cards), p));
            }
        }
        int index = 0;
        List<Integer> indexes = new ArrayList<Integer>();
        Tup<WinningHands, Face> bestSoFar = null;
        for (Tup<List<Tup<WinningHands, Face>>, Player> h : playList)
        {
            if (bestSoFar == null)
            {
                // Add first card as best so far
                bestSoFar.f = h.f.get(index).f;
                bestSoFar.l = h.f.get(index).l;
                indexes.add(index);
            }
            // New best so far
            else if (bestSoFar.f.ordinal() < h.f.get(index).f.ordinal())
            {
                bestSoFar.f = h.f.get(index).f;
                bestSoFar.l = h.f.get(index).l;
                indexes.clear();
                indexes.add(index);
            }
            // Need to break tie
            else if (bestSoFar.f.ordinal() == h.f.get(index).f.ordinal())
            {
                // Card value breaks tie no need to reorder
                if (bestSoFar.l.ordinal() > h.f.get(index).l.ordinal())
                    continue;
                // Card value breaks tie need to reorder
                else if (bestSoFar.l.ordinal() < h.f.get(index).l.ordinal())
                {
                    bestSoFar.f = h.f.get(index).f;
                    bestSoFar.l = h.f.get(index).l;
                    indexes.clear();
                    indexes.add(index);
                }
                // Card value cannot break tie
                else if (bestSoFar.l.ordinal() == h.f.get(index).l.ordinal())
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
                            for (Tup<List<Tup<WinningHands, Face>>, Player> tl : playList)
                            {
                                // only examine if its a full house
                                if (tl.f.get(0).f == WinningHands.FULL_HOUSE)
                                {
                                    // get the pair value of the full house
                                    for (Tup<WinningHands, Face> t : tl.f)
                                    {

                                        if (t.f == WinningHands.PAIR)
                                        {
                                            // first pair
                                            if (best2 == null)
                                            {
                                                best2 = t.l;
                                                pos = index;
                                            }
                                            else if (best2.ordinal() > t.l
                                                    .ordinal())
                                                continue;
                                            // new winner
                                            else if (best2.ordinal() < t.l
                                                    .ordinal())
                                            {
                                                best2 = t.l;
                                                indexes.clear();
                                                indexes.add(pos);
                                            }
                                            else if (best2.ordinal() == t.l
                                                    .ordinal())
                                            {
                                                // Add a second winner (split
                                                // pot)
                                                indexes.add(pos);
                                            }

                                        }
                                    }
                                }
                                ++pos;
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
                            p1 = playList.get(indexes.get(0)).l;
                            p2 = playList.get(index).l;
                            hand1.add(p1.getHand()[0]);
                            hand1.add(p1.getHand()[1]);
                            hand2.add(p2.getHand()[0]);
                            hand2.add(p2.getHand()[1]);
                            Face f1,
                            f2;
                            boolean won = false;
                            // Loop through 5 best cards unless tie is broken
                            for (int i = 0; i < 5; ++i)
                            {
                                f1 = th.highCard((Card[]) hand1.toArray());
                                f2 = th.highCard((Card[]) hand2.toArray());
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
                            p1 = playList.get(indexes.get(0)).l;
                            p2 = playList.get(index).l;
                            hand1.add(p1.getHand()[0]);
                            hand1.add(p1.getHand()[1]);
                            hand2.add(p2.getHand()[0]);
                            hand2.add(p2.getHand()[1]);
                            Face pairValue = playList.get(indexes.get(0)).f
                                    .get(0).l;
                            for (Card c : hand1)
                            {
                                if (c.getValue() == pairValue)
                                {
                                    hand1.remove(c);
                                }
                            }
                            for (Card c : hand2)
                            {
                                if (c.getValue() == pairValue)
                                {
                                    hand2.remove(c);
                                }
                            }
                            won = false;
                            for (int i = 0; i < 3; ++i)
                            {
                                f1 = th.highCard((Card[]) hand1.toArray());
                                f2 = th.highCard((Card[]) hand2.toArray());
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
                            p1 = playList.get(indexes.get(0)).l;
                            p2 = playList.get(index).l;
                            hand1.add(p1.getHand()[0]);
                            hand1.add(p1.getHand()[1]);
                            hand2.add(p2.getHand()[0]);
                            hand2.add(p2.getHand()[1]);
                            Face pair1 = playList.get(indexes.get(0)).f.get(0).l;
                            Face pair2 = playList.get(indexes.get(0)).f.get(1).l;
                            for (Card c : hand1)
                            {
                                if (c.getValue() == pair1
                                        || c.getValue() == pair2)
                                {
                                    hand1.remove(c);
                                }
                            }
                            for (Card c : hand2)
                            {
                                if (c.getValue() == pair1
                                        || c.getValue() == pair2)
                                {
                                    hand2.remove(c);
                                }
                            }
                            f1 = th.highCard((Card[]) hand1.toArray());
                            f2 = th.highCard((Card[]) hand2.toArray());
                            if (f1 == f2)
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
            players[i] = playList.get(i).l;
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
