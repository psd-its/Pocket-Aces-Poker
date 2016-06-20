/**
 * 
 */
package model.card;

/**
 * @author deep_thought
 *
 */
public enum WinningHands
{
    HIGH_CARD, // Highest card in play
    PAIR, // 2 cards of the same value
    TWO_PAIR, // 2 sets of 2 cards of the same values
    THREE_OF_A_KIND, // 3 cards of the same value
    STRAIGHT, // 5 cards in sequence
    FLUSH, // 5 cards of the same suit
    FULL_HOUSE, // 3 of a kind and a pair
    FOUR_OF_A_KIND, // 4 cards of the same value
    STRAIGHT_FLUSH, // 5 sequential cards in the same suit
    ROYAL_FLUSH; // Ace, king, queen, jack and ten in the same suit   
}
