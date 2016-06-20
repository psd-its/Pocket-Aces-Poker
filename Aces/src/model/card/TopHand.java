package model.card;

public class TopHand implements Best
{
    private Card[] cards;
    private WinningHands best;

    public TopHand()
    {
        // TODO Auto-generated constructor stub
        this.cards = new Card[5];
    }

    @Override
    public WinningHands processHand(Card[] cards)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    // Recursive function for finding straight
    public void straight(int start, Card[] cards) throws Straight
    {
        //Condition to end recursive function
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
                else if (c.getValue() == Face.values()[i])
                {
                    ++match;
                    break;
                }
            }
        }
        // Straight found
        if (match > Best.HAND)
        {
            throw new Straight(Face.values()[start]);
        }
        // Recursive call
        straight(--start, cards);
    }

    @Override
    public boolean flush(Card[] cards)
    {
        // counts of how many cards belong to each suit
        int hCount, dCount, sCount, cCount;
        hCount = dCount = sCount = cCount = 0;
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
        if (hCount > Best.HAND || dCount > Best.HAND || sCount > Best.HAND
                || cCount > Best.HAND)
        {
            return true;
        }
        // No flush found
        return false;
    }

}
