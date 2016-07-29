package model.facade;

import java.util.Observable;
import java.util.Observer;

import model.game.Game;
import model.game.RTM;
import model.player.Player;
import model.table.TableFull;

public class AcesModel implements AcesFacade
{
    private Game game;

    public AcesModel()
    {
        // TODO Auto-generated constructor stub
    }

    public void createGame(Game game)
    {
        // Create a game for the model
        this.game = game;

    }

    public Game getGame()
    {
        // TODO Auto-generated method stub
        return this.game;
    }

    public void playGame() throws RTM
    {
        // TODO Auto-generated method stub
        game.play();
    }

    public void setBlind(int smallBlind)
    {
        // Set the small blind, big blind is calculated
        // from this
        game.getTable().setBlind(smallBlind);
    }

    @Override
    public boolean removePlayer(Player player)
    {
        // Search for player
        for (int i = 0; i < game.getPlayers().length; ++i)
        {
            //if player found remove them from the table
            if (game.getPlayers()[i] == player)
            {
                game.getPlayers()[i] = null;
                return true;
            }
        }
        //if we get here no player has been found
        return false;

    }

    @Override
    public Player getPlayer(int seat)
    {
        // return player at the specified seat   
        return game.getPlayers()[seat];
    }

    @Override
    public void addPlayer(Player player) throws TableFull
    {
        // Add player to the table 
        game.getTable().addPlayer(player);        
    }
    
    @Override
    public void addPlayer(Player player, Observer o) throws TableFull
    {
        // Add player to the table 
        game.getTable().addPlayer(player);
        // Add the player as an observer to the game
        ((Observable)game).addObserver(o);
        
    }

}
