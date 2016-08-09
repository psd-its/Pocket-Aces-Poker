package model.card;

import java.io.Serializable;

/**
 * 
 * @author Tristan s3528615
 *
 */
public class Deck implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 7030907506729754051L;
    private Card[] cards;
    // Constructor
    public Deck()
    {
        initDeck();
    }
    /**
     * initialize a deck of cards
     */
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
    
    /**
     * Getter for deck of cards
     * @return cards
     */
    public Card[] getCards()
    {
        return cards;
        
    }
    
    /**
     * Reset the deck to its initial state
     */
    public void reset()
    {
        for (Card c : cards)
        {
            c.setInPlay(false);
            c.hide();
        }
    }
   
    
}
