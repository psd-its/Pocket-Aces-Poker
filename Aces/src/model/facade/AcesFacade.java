/**
 * 
 */
package model.facade;

import model.game.Game;
import model.game.Hand;
import model.player.Player;
import model.table.Table;

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
    public void playGame(Game game);
    
    /**
     * Update the blinds 
     * @param table
     */
    public void setBlind(Table table);
    
    /**
     * Add a player to the game
     * @param player
     */
    public void addPlayer(Player player);
    
   
}
