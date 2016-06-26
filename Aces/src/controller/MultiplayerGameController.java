/**
 * Concrete controller class for starting a new multiplayer game.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;
import view.main.MainView;
import view.screen.StartScreen;

public class MultiplayerGameController extends AbsNewGameController
{
    /**
     * Constructor, just calls super for now.
     * 
     * @param MainView A reference to the main view, sent to superclass 
     * constructor.
     */
    public MultiplayerGameController(MainView mainView, StartScreen startScreen)
    {
        super(mainView, startScreen);
    }

    @Override 
    public void actionPerformed(ActionEvent e)
    {
       switch(e.getActionCommand())
       {
           case StartScreen.MULTIPLAYER_BUTTON :
               break;
           
           default :
               break;
       }
    }
}
