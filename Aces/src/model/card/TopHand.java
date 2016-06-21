package model.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TopHand implements Process
{
    // Constructor
    public TopHand()
    {
        // TODO Auto-generated constructor stub

    }

    @Override
    public List<Pair<WinningHands, Face>> processHand(Card[] cards)
    {
        // Datastructures
        List<Pair<WinningHands, Face>> matches = new ArrayList<Pair<WinningHands, Face>>();
        List<Pair<Face, Integer>> multiples = getMultiples(cards);

        // 0 or 1 multiples found
        if (multiples.size() < 2)
        {
            Card[] best = flush(cards);
            if (best != null)
            {
                try
                {
                    straight(Process.START, best);
                    matches.add(new Pair<WinningHands, Face>(
                            WinningHands.FLUSH, highCard(best)));
                    return matches;
                }
                catch (Straight s)
                {
                    // test for royal flush
                    if (s.getHighCard() == Face.ACE)
                    {
                        matches.add(new Pair<WinningHands, Face>(
                                WinningHands.ROYAL_FLUSH, Face.ACE));
                        return matches;

                    }
                    // test for straight flush
                    else
                    {
                        matches.add(new Pair<WinningHands, Face>(
                                WinningHands.STRAIGHT_FLUSH, s.getHighCard()));
                        return matches;
                    }
                }

            }
            else
            {
                try
                {
                    straight(Process.START, cards);
                }
                catch (Straight s)
                {
                    // add straight
                    matches.add(new Pair<WinningHands, Face>(
                            WinningHands.STRAIGHT, s.getHighCard()));
                    return matches;
                }
            }
            // get high card (called bluff win condition)
            if (multiples.size() == 0)
            {
                matches.add(new Pair<WinningHands, Face>(
                        WinningHands.HIGH_CARD, highCard(cards)));
                return matches;
            }
            // Process matched values
            for (int i = 0; i < multiples.size(); ++i)
            {
                switch (multiples.get(i).l)
                {
                    case 2:
                        matches.add(new Pair<WinningHands, Face>(
                                WinningHands.PAIR, multiples.get(i).f));
                        break;
                    case 3:
                        matches.add(new Pair<WinningHands, Face>(
                                WinningHands.THREE_OF_A_KIND,
                                multiples.get(i).f));
                        break;
                    case 4:
                        matches.add(new Pair<WinningHands, Face>(
                                WinningHands.FOUR_OF_A_KIND, multiples.get(i).f));
                        return matches;
                     
                }
            }
            if (matches.size() > 1)
            {
                int pairCount, threeCount, fourCount;
                pairCount = threeCount = fourCount = 0;
                for (Pair<WinningHands, Face> m : matches)
                {
                    switch (m.f)
                    {
                        case PAIR:
                            ++pairCount;
                            break;
                        case THREE_OF_A_KIND:
                            ++threeCount;
                            break;
                        case FOUR_OF_A_KIND:
                            ++fourCount;
                            break;
                    }
                }
                if (pairCount > 1 && threeCount == 0)
                {
                    matches.add(0, new Pair<WinningHands, Face>(
                            WinningHands.TWO_PAIR, highCard(cards)));
                }
                

            }

        }

        return matches;
    }

    // Recursive function for finding straight
    private void straight(int start, Card[] cards) throws Straight
    {
        // Failure condition that ends recursive function
        if (start < 5) return;
        // count
        int match = 0;
        for (int i = start; i > start - 5; --i)
        {
            for (Card c : cards)
            {
                // Handles low straight ace -> 5
                if (i < 0 && match > 3)
                {
                    if (c.getValue() == Face.ACE)
                    {
                        throw new Straight(Face.values()[start]);
                    }
                }
                // Handles all other straights
                else if (c.getValue() == Face.values()[i])
                {
                    ++match;
                    break;
                }
            }
        }
        // Straight found
        if (match > Process.HAND)
        {
            throw new Straight(Face.values()[start]);
        }
        // Recursive call
        straight(--start, cards);
    }

    private Card[] flush(Card[] cards)
    {
        // counts of how many cards belong to each suit
        int hCount, dCount, sCount, cCount;
        hCount = dCount = sCount = cCount = 0;
        Card[] flush = null;
        // Step through the cards in the hand
        for (Card c : cards)
        {
            // count the suits
            switch (c.getSuit())
            {
                case HEARTS:
                    ++hCount;
                    break;
                case DIAMONDS:
                    ++dCount;
                    break;
                case SPADES:
                    ++sCount;
                    break;
                case CLUBS:
                    ++cCount;
                    break;
            }
        }
        // Check for a flush
        if (hCount > Process.HAND)
        {
            flush = sortSuit(cards, Suit.HEARTS);
        }
        else if (dCount > Process.HAND)
        {
            flush = sortSuit(cards, Suit.DIAMONDS);
        }
        else if (sCount > Process.HAND)
        {
            flush = sortSuit(cards, Suit.SPADES);
        }
        else if (cCount > Process.HAND)
        {
            flush = sortSuit(cards, Suit.CLUBS);

        }
        // No flush found if null
        return flush;
    }

    // helper function for flush
    // @SuppressWarnings("null")
    private Card[] sortSuit(Card[] cards, Suit suit)
    {
        List<Card> flush = new ArrayList<Card>();
        for (Card c : cards)
        {
            if (c.getSuit() == suit)
            {
                flush.add(c);
            }
        }
        if (flush.size() == 5)
        {
            return flush.toArray(new Card[5]);
        }
        // remove the lowest cards in the flush
        while (flush.size() > 5)
        {
            // Card value and index in the hand
            Pair<Face, Integer> card = null;
            for (int i = 0; i < flush.size(); ++i)
            {
                if (card == null)
                {
                    card.f = flush.get(i).getValue();
                    card.l = i;
                }
                else if (flush.get(i).getValue().ordinal() < card.f.ordinal())
                {
                    card.f = flush.get(i).getValue();
                    card.l = i;
                }
            }
            flush.remove(card.l);
        }
        return flush.toArray(new Card[5]);

    }

    private List<Pair<Face, Integer>> getMultiples(Card[] cards)
    {
        // Growable data structure for storing duplicate cards in the hand
        List<Pair<Face, Integer>> doubles = new ArrayList<Pair<Face, Integer>>();
        int cardCount = 1;
        // Step through the cards in the hand
        for (int i = 0; i < cards.length; ++i)
        {
            // Check the rest of the hand for a match
            for (int j = i; j < cards.length; ++j)
            {

                if (cards[i].getValue() == cards[j].getValue())
                {
                    ++cardCount;
                }
            }
            // if a patch was found add it to the list
            if (cardCount > 1)
            {
                doubles.add(new Pair<Face, Integer>(cards[i].getValue(),
                        cardCount));
            }
            // reset the card count
            cardCount = 1;
        }
        return doubles;

    }

    /**
     * Get the highest card in the players hand. This is public as it may need
     * to be used for tie breaks.
     * 
     * @param cards
     * @return highest card
     */
    @Override
    public Face highCard(Card[] cards)
    {
        Face bestCard = null;
        // step through cards
        for (Card c : cards)
        {
            // ensure that we are dealing with a valid card
            if (c != null)
            {
                // set the best card so far
                if (bestCard == null)
                {
                    bestCard = c.getValue();
                }
                // if current card beats the current best set it as the best
                // card so far
                else if (c.getValue().ordinal() > bestCard.ordinal())
                {
                    bestCard = c.getValue();
                }
            }
        }

        return bestCard;

    }

}

/**
 * Mock Tuple representation
 * 
 * @author Tristan s3528615
 */
class Pair<F, L>
{
    public F f;
    public L l;

    public Pair(F first, L last)
    {
        this.f = first;
        this.l = last;
    }
}
