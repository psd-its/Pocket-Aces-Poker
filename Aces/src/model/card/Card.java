package model.card;


public class Card
{
    // types are defined as enums
    private Suit suit;
    private Face face;
    private boolean inPlay;
    // Constructor
    public Card(Suit suit, Face face)
    {
        this.suit = suit;
        this.face = face;
        this.inPlay = false;
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
}
