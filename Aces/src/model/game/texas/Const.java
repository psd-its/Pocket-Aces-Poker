/**
 * @author Tristan s3528615
 */
package model.game.texas;

/**
 * Game Constants
 */
public interface Const
{
    /**
     * Amount if the starting blind
     */
    public static final int START_BLIND = 25;
   
    /**
     * constant for the player that has the small blind
     */
    public static final int SMALL_BLIND = 0; 
    
    /**
     * constant for the player that has the big blind
     */
    public static final int BIG_BLIND = 1;
    
    /**
     * constant for the player that is the dealer
     */
    public static final int DEALER = 9;
    
    /**
     * constant for the number of cards in the flop
     */
    public static final int FLOP = 3;
}
