package model.table;

import model.card.Card;
import model.card.Deck;
import model.player.Player;

public interface Table
{
    //table constants
    public static final int MAX_PLAYERS = 10;
    
    public static final int START_BLIND = 25;
    
    public static final int MAX_CARDS = 5;
    
    public static final int BURN_ONE = 0;
    
    public static final int BURN_TWO = 3;
    
    public static final int BURN_THREE = 4;
    
    public void dealCards();
    
    public boolean placeBet(Player player, int ammount);
    
    public Player[] getSeats();
    
    public void addPlayer(Player player);
    
    public int getPot();
    
    public void addToPot(int bet);
    
    public int getBlind();
    
    public void setBlind(int blind);
    
    public Deck getDeck();
    
    /**
     * Get the seat of the current dealer
     * @return seatNumber
     */
    public int getDealer();
    
    /**
     * Set the seat position of the current dealer for this hand
     * @param dealer
     */
    public void setDealer(int dealer);
    
    /**
     * Texas holdem function
     * @return shared cards
     */
    public Card[] getCardsInPlay();
    
    /**
     * Texas holdem tester function that sets the shared
     * cards 
     */
    public void setCardsInPlay(Card[] cards);
    
    /**
     * The current bet amount expected from each
     * player for this round of betting
     * @return current bet amount
     */
    public int getCurrentBet();
    
    /**
     * Set the bet amount expected from each player
     * to stay in the hand
     * @param bet amount
     */
    public void setCurrentBet(int bet);
    
    /**
     * Count players still playing at the table
     * @return active player count
     */
    public int playerCount();

    /**
     * Set the pot back to 0 for a new round
     */
    public void resetPot();
    
}
