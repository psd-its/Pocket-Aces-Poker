package model.table;

import model.player.Player;

public interface Table
{
    public static final int MAX_PLAYERS = 10;
    
    public static final int START_BLIND = 25;
    
    public void dealCards();
    
    public boolean placeBet(Player player, int ammount);
}
