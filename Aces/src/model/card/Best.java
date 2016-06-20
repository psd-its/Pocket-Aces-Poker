/**
 * @author Tristan s3528615
 */
package model.card;

public interface Best
{
    public static final int HAND = 4; 
    /**
     * Process the players hand to determine 
     * the best hand they can make
     * @param cards
     * @return bestHand
     */
    public WinningHands processHand(Card[] cards);
    
    public void straight(int start, Card[] cards) throws Straight;
    
    public boolean flush(Card[] cards);
    
    
}
