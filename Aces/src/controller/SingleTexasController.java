/**
 * Concrete controller class for a single player texas hold 'em game. Create 
 * required computer players and add them to the game. Perform any onther setup
 * tasks and then transfer to the actual game playing screen.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;
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
            // TODO init computer players
            // TODO add comp players to game
            // TODO transfer to actual game playing screen
        }
    }
}
