package model.card;

/**
 * 
 * @author deep_thought
 *
 */
public class Deck
{
    private Card[] cards;
    // Constructor
    public Deck()
    {
        initDeck();
    }
    //initialize a deck of cards
    private void initDeck()
    { 
        int count = 0;
        this.cards = new Card[52];
        for (Suit s : Suit.values())
        {
            for (Face f : Face.values())
            {
                cards[count++] = new Card(s, f);
            }
        }
        
    }
    public Card[] getDeck()
    {
        return cards;
    }
   
    
}
