/**
 * 
 */
package model.game;

import model.player.Player;
import model.table.Table;

/**
 * @author Tristan s3528615
 *
 */
public interface Game
{
    /**
     * Add a concrete Table class for the version
     * of poker that applies to that game 
     * @param table
     */
    public void addTable(Table table);
    /**
     * Add a player to the game
     * @param player
     */
    public void addPlayer(Player player);
    
    /**
     * Returns players at the table
     * @return players
     */
    public Player[] getPlayers();
    
    /**
     * Deal cards in the format specific to the format 
     * for the card game that applies
     */
    public void dealCards();
    
    /**
     * Human will need input from controller 
     * @param player
     */
    public void takeTurn(Player player);
    /**
     * If in NOWINNER is returned it is a split pot in texas hold'em 
     * @param players
     * @return handWon
     */
    public Hand checkForWinner(Player player);
    
    
}
