/**
 * @author Tristan s3528615
 */
package model.card;

import java.util.List;

public interface Process
{
    public static final int HAND = 4; 
    public static final int START = 12; 
    /**
     * Process the players hand to determine 
     * the best hand they can make
     * @param cards
     * @return bestHand
     */
    public List<Pair<WinningHands, Face>> processHand(Card[] cards);
    
    /**
     * Get the highest card in the players hand.
     * This is public as it may need to be used 
     * for tie breaks.
     * @param cards
     * @return highest card
     */
    public Face highCard(Card[] cards);
}
