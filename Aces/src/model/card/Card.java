package model.card;

import java.io.Serializable;


public class Card implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -8815483008742358415L;
    // types are defined as enums
    private Suit suit;
    private Face face;
    private boolean inPlay;
    private boolean faceUp;
    // Constructor
    public Card(Suit suit, Face face)
    {
        this.suit = suit;
        this.face = face;
        this.inPlay = false;
        this.faceUp = false;
    }
    // Accessors
    public Suit getSuit()
    {
        return suit;
    }
    
    public Face getValue()
    {
        return face;
    }
    
    public void show()
    {
        faceUp = true;
    }
    
    public void hide()
    {
        faceUp = false;
    }
    
    public boolean isInPlay()
    {
        return inPlay;
    }
    
    public void setInPlay(boolean inPlay)
    {
        this.inPlay = inPlay;
    }
    
    public boolean isShowing()
    {
        return faceUp;
    }
    @Override
    public String toString()
    {
        return  face + " of " + suit;
    }
    
    
}
