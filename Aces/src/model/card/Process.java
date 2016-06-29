/**
 * @author Tristan s3528615
 */
package model.card;

import java.util.List;

public interface Process
{
    public static final int HAND = 4; 
    public static final int START = 12; 
    public static final int HOLE_L = 5;
    public static final int HOLE_R = 6;
    /**
     * Process the players hand to determine 
     * the best hand they can make
     * @param cards
     * @return bestHand
     */
    public List<Tup<WinningHands, Face>> processHand(Card[] cards);
    
    /**
     * Get the highest card in the players hand.
     * This is public as it may need to be used 
     * for tie breaks.
     * @param cards
     * @return highest card
     */
    public Face highCard(Card[] cards);
    
    /**
     * Get vard values and the number of times they repeat in
     * the hand passed in
     * @param cards
     * @return values that appear more then once
     */
    public List<Tup<Face, Integer>> getMultiples(Card[] cards);
}
