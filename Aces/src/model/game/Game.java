/**
 * 
 */
package model.game;

import model.card.TopHand;
import model.player.Player;
import model.table.Table;
import model.table.TableFull;

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
     * Get access to the functions for the current table
     * @return Table
     */
    public Table getTable();
    
    /**
     * Add a player to the game
     * @param player
     * @throws TableFull 
     */
    public void addPlayer(Player player) throws TableFull;
    
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
     * @throws Exception 
     */
    public Player[] checkForWinner() throws Exception;
    
    /**
     * Initiates the game
     */
    public void play();
    
    /**
     * @return the Thread
     */
    public Thread getThread();
    
    public TopHand getTh();

   
    
    
}
