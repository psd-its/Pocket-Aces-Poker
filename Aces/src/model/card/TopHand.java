package model.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TopHand implements Process
{
    // Constructor
    public TopHand()
    {
        // 
    }

    @Override
    public List<Tup<WinningHands, Face>> processHand(Card[] cards)
    {
        // Data structures
        List<Tup<WinningHands, Face>> matches = new ArrayList<Tup<WinningHands, Face>>();
        List<Tup<Face, Integer>> multiples = getMultiples(cards);

        // 0 or 1 multiples found
//        if (multiples.size() <= 2)
//        {
            Card[] best = flush(cards);
            if (best != null)
            {
                try
                {
                    straight(Process.START, best);
                    matches.add(new Tup<WinningHands, Face>(
                            WinningHands.FLUSH, highCard(best)));
                    return matches;
                }
                catch (Straight s)
                {
                    // test for royal flush
                    if (s.getHighCard() == Face.ACE)
                    {
                        matches.add(new Tup<WinningHands, Face>(
                                WinningHands.ROYAL_FLUSH, Face.ACE));
                        return matches;

                    }
                    // test for straight flush
                    else
                    {
                        matches.add(new Tup<WinningHands, Face>(
                                WinningHands.STRAIGHT_FLUSH, s.getHighCard()));
                        return matches;
                    }
                }

            }
//            else
//            {
                try
                {
                    straight(Process.START, cards);
                }
                catch (Straight s)
                {
                    // add straight
                    matches.add(new Tup<WinningHands, Face>(
                            WinningHands.STRAIGHT, s.getHighCard()));
                    return matches;
                }
//            }
            // get high card (called bluff win condition)
            if (multiples.size() == 0)
            {
                matches.add(new Tup<WinningHands, Face>(
                        WinningHands.HIGH_CARD, highCard(cards)));
                return matches;
            }
            // Process matched values
            for (int i = 0; i < multiples.size(); ++i)
            {
                switch (multiples.get(i).l)
                {
                    case 2:
                        System.out.println("Pair added");
                        matches.add(new Tup<WinningHands, Face>(
                                WinningHands.PAIR, multiples.get(i).f));
                        break;
                    case 3:
                        System.out.println("Three of a kind added");
                        matches.add(new Tup<WinningHands, Face>(
                                WinningHands.THREE_OF_A_KIND,
                                multiples.get(i).f));
                        break;
                    case 4:
                        System.out.println("Four of a kind added");
                        matches.add(new Tup<WinningHands, Face>(
                                WinningHands.FOUR_OF_A_KIND, multiples.get(i).f));
                        return matches;                     
                }
            }
            // This is done so we can easily see the best hand ie full house we only 
            // need to worry about card values if someone else has a matching hand
            if (matches.size() > 1)
            {
                int pairCount, threeCount, fourCount;
                pairCount = threeCount = fourCount = 0;
                for (Tup<WinningHands, Face> m : matches)
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
                        default:
                            break;
                    }
                }
                if (pairCount > 1 && threeCount == 0)
                {
                    System.out.println("Two pair added");
                    matches.add(0, new Tup<WinningHands, Face>(
                            WinningHands.TWO_PAIR, multiples.get(0).f));
                }
                // there can only be 5 cards in a hand so remove the lower triple
                else if (threeCount > 1 && pairCount == 0)
                {
                    if (matches.get(0).l.ordinal() > matches.get(1).l.ordinal())
                    {
                        matches.remove(1);
                    }
                    else 
                    {
                        matches.remove(0);
                    }
                }
                // Full house
                else if (threeCount == 1 && pairCount > 0)
                {
                    Face high = null;
                    for ( Tup<WinningHands, Face> p: matches)
                    {
                        if (p.f == WinningHands.THREE_OF_A_KIND)
                        {
                            high = p.l;
                        }
                    }
                    matches.add(0, new Tup<WinningHands, 
                            Face>(WinningHands.FULL_HOUSE, high));
                }
            }
            return matches;
        }

        
   // }

    // Recursive function for finding straight
    private void straight(int start, Card[] cards) throws Straight
    {
        //System.out.println("Straight() called: checking for strainght");
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
        //System.out.println("flush() called: checking for flush");
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
    private Card[] sortSuit(Card[] cards, Suit suit)
    {
        System.out.println("sortSuit() called: sorting flush by suit");
        List<Card> flush = new ArrayList<Card>();
        // Store all cards that match the suit passed in as a list
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
            System.out.println("The size of the flush is: " + flush.size());
            // Card value and index in the hand
            Tup<Face, Integer> card = null;
            // Loop through and find the lowest card
            for (int i = 0; i < flush.size(); ++i)
            {
                if (card == null)
                {
                    card = new Tup<Face, Integer>(flush.get(i).getValue(), i);
                }
                else if (flush.get(i).getValue().ordinal() < card.f.ordinal())
                {
                    card = new Tup<Face, Integer>(flush.get(i).getValue(), i);
                }
            }
            // Remove the lowest card
            int index = card.l;
            flush.remove(index);
        }
        return flush.toArray(new Card[5]);

    }

    public List<Tup<Face, Integer>> getMultiples(Card[] cards)
    {
        //System.out.println("getMultiples() called: checking for pairs etc");
        // Growable data structure for storing duplicate cards in the hand
        List<Tup<Face, Integer>> doubles = new ArrayList<Tup<Face, Integer>>();
        int cardCount = 1;
        // Step through the cards in the hand
        for (int i = 0; i < cards.length; ++i)
        {
            // Check the rest of the hand for a match
            for (int j = 1 + i; j < cards.length; ++j)
            {
               // System.out.printf("i = %s : j = %s\n", i, j);
                if (cards[i].getValue() == cards[j].getValue())
                {
                 //   System.out.printf("%s ", cards[i].toString());
                    System.out.printf("matches %s\n", cards[j].toString());
                    ++cardCount;
                }
            }
            // if a patch was found add it to the list
            if (cardCount > 1)
            {
                doubles.add(new Tup<Face, Integer>(cards[i].getValue(),
                        cardCount));
            }
            // reset the card count
            cardCount = 1;
        }
        System.out.println("count of multiples: " + doubles.size());
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
        //System.out.println("highCard() called: checking for the highest card");
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


