/**
 * Concrete controller class for a 'single player texas hold 'em' game. Create 
 * required computer players and add them to the game. Perform any onther setup
 * tasks and then transfer to the game playing screen.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;
import model.player.ComputerPlayer;
import model.player.Player;
import model.table.TableFull;
import view.main.MainView;
import view.screen.AbsGameScreen;
import view.screen.SinglePlayerTexasScreen;

public class SingleTexasController extends AbsGameSetupController
{
    public SingleTexasController(AbsGameScreen gameSetupScreen)
    {
        super(gameSetupScreen);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(SinglePlayerTexasScreen.START_BUTTON))
        {  
            try
            {
                // need to cast 'abstract game' screen to 'single poker game' screen
                SinglePlayerTexasScreen screen = (SinglePlayerTexasScreen)this.gameSetupScreen;
                
                // determine number of AI players requested
                int numCompPlayers = screen.getNumCompPlayers();
                
                // add AI players to game
                for(int i = 0; i < numCompPlayers; i++)
                {
                    // init comp player
                    Player newPlayer = new ComputerPlayer("HAL" + i); 
                    
                    // add comp player
                    super.getFacade().addPlayer(newPlayer);
                }
                
                // transfer to game playing screen - single texas poker game should be fully initialised
                super.switchScreen(MainView.TEXAS_GAME_SCREEN);
                
            }
            
            catch(TableFull exception)
            {
                // TODO Handle this - what is procedure for handling this?
                System.err.println("Table Full");
                super.switchScreen(MainView.START_SCREEN);
            }
        }
    }
}
