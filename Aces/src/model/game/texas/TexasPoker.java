package model.game.texas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import model.card.Card;
import model.card.Face;
import model.card.Process;
import model.card.TopHand;
import model.card.Tup;
import model.card.WinningHands;
import model.game.Game;
import model.game.RTM;
import model.player.Player;
import model.player.TurnTimer;
import model.table.Table;
import model.table.TableFull;

/**
 * 
 * @author Tristan s3528615
 * 
 */
public class TexasPoker extends Observable implements Game
{
    /**
     * 
     */
    private static final long serialVersionUID = 3646392568658961652L;
    private TopHand th;
    private Table table;
    private Player currentPlayer; 
    // static CyclicBarrier bar;
    private TurnTimer t;

    // private Timer texThread;

    public TexasPoker()
    {
        // Instantiate a new thread and an instance
        // of top hand for processing hands
        this.th = new TopHand();

        this.t = new TurnTimer();
       

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
        // var for holding cards
        Card[] cards = new Card[7];
        System.arraycopy(table.getCardsInPlay(), 0, cards, 0, 5);
        // get players
        // Add the players cards to the array containing the shared cards
        for (Player p : table.getSeats())
        {
            if (p != null && p.isPlaying())
            {
                cards[Process.HOLE_L] = p.getHand()[0];
                cards[Process.HOLE_R] = p.getHand()[1];
                // // Print the cards for this player DEBUG
                // for (Card c : cards)
                // {
                // if (c != null)
                // System.out.println("card: " + c.toString());
                // else
                // System.out.println("card: NULL ?!?");
                //
                // }
                List<Tup<WinningHands, Face>> best = th.processHand(cards);
                if (best.isEmpty())
                {
                    throw new Exception(
                            "Error! - processHand has not returned a result");
                }
                p.setBestHand(best.get(0));

            }
        }
        int index = 0;
        // List holds the seat position of a player
        List<Integer> indexes = new ArrayList<Integer>();
        // Tuple of best hand so far
        Tup<WinningHands, Face> bestSoFar = null;
        // Vars to make comparison cleaner
        Face f1 = null, f2 = null;
        for (Player h : table.getSeats())
        {
            // fix the null pointer on first round fold:
            if (h == null || !h.isPlaying())
            {
                ++index; // index wasn't being incremented
                continue;
            }

            /**
             * this is the original:
             * 
             * if(h == null || !h.isPlaying()) continue;
             * 
             * so breaking out of the for loop skips incrementing 'index'
             * 
             */

            // First iteration so best so far not set
            if (bestSoFar == null)
            {
                // Add first card as best so far
                bestSoFar = new Tup<WinningHands, Face>(h.getBestHand().f,
                        h.getBestHand().l);

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
                                switch (i)
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
                                f1 = th.highCard(hand1.toArray(new Card[hand1
                                        .size()]));
                                f2 = th.highCard(hand2.toArray(new Card[hand2
                                        .size()]));
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
                            for (Iterator<Card> it = hand1.iterator(); it
                                    .hasNext();)
                            {
                                c = it.next();
                                if (c.getValue() == bestSoFar.l)
                                {
                                    it.remove();
                                }
                            }
                            for (Iterator<Card> it = hand2.iterator(); it
                                    .hasNext();)
                            {
                                c = it.next();
                                if (c.getValue() == bestSoFar.l)
                                {
                                    it.remove();
                                }
                            }
                            // Place holder
                            won = false;
                            // Compare the rest of the cards looking for high
                            // card
                            for (int i = 0; i < 3; ++i)
                            {
                                f1 = th.highCard(hand1.toArray(new Card[hand1
                                        .size()]));
                                f2 = th.highCard(hand2.toArray(new Card[hand2
                                        .size()]));
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
                            Face p1_pair1,
                            p1_pair2,
                            p2_pair1,
                            p2_pair2;
                            p1_pair1 = p1_pair2 = p2_pair1 = p2_pair2 = null;
                            List<Tup<Face, Integer>> p1_multiples,
                            p2_multiples;
                            p1_multiples = th.getMultiples(hand1
                                    .toArray(new Card[hand1.size()]));
                            p2_multiples = th.getMultiples(hand2
                                    .toArray(new Card[hand2.size()]));
                            // both hands on have 2 pairs
                            if (p1_multiples.size() == 2
                                    && p2_multiples.size() == 2)
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
                                            if (p1_pair1.ordinal() > p1_pair2
                                                    .ordinal())
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
                            if (p1_pair1 == p2_pair1 || p1_pair1 == p2_pair2)
                            {
                                // both pairs match
                                if (p1_pair2 == p2_pair1
                                        || p1_pair2 == p2_pair2)
                                {
                                    // remove pairs from the
                                    for (Iterator<Card> it = hand1.iterator(); it
                                            .hasNext();)
                                    {
                                        c = it.next();
                                        if (c.getValue() == p1_pair1
                                                || c.getValue() == p1_pair2)
                                        {
                                            it.remove();
                                        }
                                    }
                                    for (Iterator<Card> it = hand2.iterator(); it
                                            .hasNext();)
                                    {
                                        c = it.next();
                                        if (c.getValue() == p2_pair1
                                                || c.getValue() == p2_pair2)
                                        {
                                            it.remove();
                                        }
                                    }
                                    f1 = th.highCard(hand1
                                            .toArray(new Card[hand1.size()]));
                                    f2 = th.highCard(hand2
                                            .toArray(new Card[hand2.size()]));

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
            // if (table.getSeats()[indexes.get(i)] != null)
            // {
            // System.out.println(table.getSeats()[indexes.get(i)].getName()
            // + " is playing " + table.getSeats()[indexes.get(i)].isPlaying());
            // }
            if (table.getSeats()[indexes.get(i)] != null)
            // && table.getSeats()[indexes.get(i)].isPlaying())
            {
                players[i] = table.getSeats()[indexes.get(i)];
            }
        }
        // Print the winner(s)
        for (Player p : players)
        {
            System.out.println(p.getName() + " with a " + p.getBestHand().f
                    + " of " + p.getBestHand().l + "s");
        }
        return players;
    }

    @Override
    public void takeTurn(Player player) throws RTM
    {
        // Ensure we are dealing with a valid player that is playing the hand
        if (player == null || !player.isPlaying()) return;
        player.playHand(this);

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
    public void play() throws RTM // RTM = Return to menu
    {
        // infinite table
        boolean progress, firstIter;
        int playersIn = 0;
        // ensure there is at least 2 players
        while (table.playerCount() > 1)
        {
            // reset the pot and current bet
            table.resetBets();
            table.resetPot();
            // Deal the cards
            dealCards();
            this.setChanged();      // this will need to be called after every change in game state
            this.notifyObservers(); // this will need to be called after every change in game state
            //this.notifyObservers(this);
            firstIter = true;
            // // Print header
            // System.out.printf("%s\n%-10s\n%s\n", Const.BREAK,
            // Const.SSTRING, Const.BREAK);
            // Go through the stages of betting
            for (Bet stage : Bet.values())
            {
                // Flag for progressing to the next
                // stage of betting
                progress = false;
                // Clean slate for new round of betting
                table.resetBets();
                // Loop for ensuring all players meet the minimum bet
                // requirements for the current round of betting
                while (!progress)
                {
                    // index of the players seat that is left of dealer
                    int index = table.getDealer() + 1;
                    // Loop through all seats at the table
                    for (int i = 0; i < getTable().getSeats().length; ++i)
                    {
                        // Avoid array out of bounds
                        if (index >= getTable().getSeats().length)
                        {
                            index = 0;
                        }
                        // ensure we are looking at a valid player
                        if (getTable().getSeats()[index] != null)
                        {
                            //set the current player
                            //currentPlayer = getTable().getSeats()[index];
                            // Set blinds
                            if (stage == Bet.FLOP && firstIter)
                            {
                                // pay small blind
                                if (i == Const.SMALL_BLIND)
                                {
                                    getTable().getSeats()[index]
                                            .placeBet(Const.START_BLIND);
                                    getTable().setCurrentBet(Const.START_BLIND);
                                    // System.out.println(table.getSeats()[index].getName()
                                    // +
                                    // " pays small blind of $" +
                                    // (int)Const.START_BLIND);

                                }
                                // pay big blind
                                else if (i == Const.BIG_BLIND)
                                {
                                    getTable().getSeats()[index]
                                            .placeBet(Const.START_BLIND * 2);
                                    getTable().setCurrentBet(
                                            Const.START_BLIND * 2);
                                    // System.out.println(table.getSeats()[index].getName()
                                    // +
                                    // " pays small blind of $"
                                    // + (2 * (int)Const.START_BLIND));
                                }
                                // only give players turn if blinds have been
                                // payed
                                else
                                {
                                    takeTurn(table.getSeats()[index]);
                                    
                                    // added pause between players bets
                                    this.setChanged();
                                    this.notifyObservers(this);
                                    try
                                    {
                                        Thread.sleep(1000);
                                    }
                                    catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }

                            }
                            else
                            {
                                // Let the player have there turn
                                takeTurn(table.getSeats()[index]);
                                
                                // added pause between players bets
                                this.setChanged();
                                this.notifyObservers(this);
//                                try
//                                {
//                                    Thread.sleep(2000);
//                                }
//                                catch (InterruptedException e)
//                                {
//                                    e.printStackTrace();
//                                }
                            }

                        }
                        // we are back at the dealer
                        if (i == Table.MAX_PLAYERS - 1)
                        {
                            boolean moreBets = false;
                            // Check everyone playing has met the current min
                            // bet for this round of betting
                            for (Player p : table.getSeats())
                            {
                                if (p != null && p.isPlaying())
                                {
                                    // there is a player that has not met the
                                    // minimum bet
                                    if (p.getCurrentBet() != table
                                            .getCurrentBet())
                                    {
                                        moreBets = true;
                                    }
                                }
                            }
                            // All players have met the min bet so progress
                            // with the next stage
                            if (!moreBets)
                            {
                                progress = true;
                            }
                        }

                        // move to the next player
                        ++index;
                    }
                    firstIter = false;
                }
                // Count how many players are still in the round
                playersIn = 0;
                for (Player p : table.getSeats())
                {
                    if (p != null && p.isPlaying())
                    {
                        ++playersIn;
                    }
                }
                // All but 1 player have folded
                if (playersIn < 2)
                {
                    break;
                }

                switch (stage)
                {
                    case FLOP:
                        // Show first 3 cards
                        for (int i = 0; i < Const.FLOP; ++i)
                        {
                            table.getCardsInPlay()[i].show();
                        }
                        break;
                    case TURN:
                        // Show the turn
                        table.getCardsInPlay()[3].show();
                        break;
                    case RIVER:
                        // Show the river
                        table.getCardsInPlay()[4].show();
                        break;
                    case LAST:
                        // Players have been called so show there cards
                        for (Player p : table.getSeats())
                        {
                            // check valid player still in game
                            if (p != null && p.isPlaying())
                            {

                                for (Card c : p.getHand())
                                {
                                    c.show();
                                }
                                // System.out.println(p.toString());
                            }
                        }
                        // System.out.println();
                        break;
                }
            }

            try
            {
                // Check for a winner of the pot
                Player[] winners = checkForWinner();
                // Account for split pot
                int amount = table.getPot() / winners.length;
                // System.out.println("Pot: " + amount + "\nWon by:");
                for (Player p : winners)
                {
                    System.out.println(p.getName());
                    p.addCash(amount);
                }
                
                // ADDED BY MAT
                for (Player p : table.getSeats())
                {
                    if (p != null)
                    {
                        for (Card c : p.getHand())
                        {
                            c.show();
                        }
                    }
                }
                this.setChanged();
                this.notifyObservers(this);
                Thread.sleep(4000);
                // END ADDED BY MAT
                
                // System.out.println();
                // move the dealer button
                int button = table.getDealer() + 1;
                table.setDealer(button);

            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public TopHand getTh()
    {
        return th;
    }

     /**
     * @return the Timer for a human players turn
     */
    public TurnTimer getTimer()
    {
        return t;
    }

    @Override
    public String getCurrentPlayer()
    {
        // player is null
        if (currentPlayer == null)
            return null;
        // player is valid so get there name
        return currentPlayer.getName();
    }

}
