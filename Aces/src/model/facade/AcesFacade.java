/**
 * 
 */
package model.facade;

import java.io.Serializable;
import java.util.Observer;

import model.game.Game;
import model.game.RTM;
import model.player.Player;
import model.table.TableFull;

/**
 * @author Tristan s3528615
 *
 */
public interface AcesFacade extends Serializable
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
     * @throws RTM 
     */
    public void playGame() throws RTM;
    
    /**
     * Update the blinds 
     * @param table
     */
    public void setBlind(int smallBlind);
    
    /**
     * Add a computer player to the game
     * @param player
     */
    public void addPlayer(Player player) throws TableFull;
    
    /**
     * Add a human player to the game
     * @param player
     * @param Observer that is watching the game
     */
    public void addPlayer(Player player, Observer o) throws TableFull;
    
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
