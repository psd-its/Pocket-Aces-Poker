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
    
    public int getDealer();
    
    public void setDealer(int dealer);
    
    public Card[] getCardsInPlay();
    
    
}
