/**
 * 
 */
package model.facade;

import model.game.Game;
import model.player.Player;
import model.table.TableFull;

/**
 * @author Tristan s3528615
 *
 */
public interface AcesFacade
{
    /**
     * Create a version of poker to play
     * @param game
     */
    public void createGame(Game game);
    
    /**
     * Return instance of the current game 
     * @return game
     */
    public Game getGame();
    
    /**
     * game loop
     * @param game
     */
    public void playGame();
    
    /**
     * Update the blinds 
     * @param table
     */
    public void setBlind(int smallBlind);
    
    /**
     * Add a player to the game
     * @param player
     */
    public void addPlayer(Player player) throws TableFull;
    
    /**
     * Remove a player from the table
     * @param player
     * @return true if player removed
     */
    public boolean removePlayer(Player player);
    
    /**
     * Returns the player at a given seat
     * @param seat
     * @return Player
     */
    public Player getPlayer(int seat);
   
}
