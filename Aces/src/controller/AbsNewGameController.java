/**
 * Abstract controller class for starting a new game.
 * 
 * @author Mathew Harrington
 **/

package controller;

import java.awt.event.ActionListener;
import view.main.MainView;
import view.screen.StartScreen;

public abstract class AbsNewGameController implements ActionListener
{
    private MainView mainView;
    private StartScreen startScreen;
    
    /**
     * Base constructor, allocates the main view reference.
     * 
     * @param MainView A reference to the main window of the application.
     */
    public AbsNewGameController(MainView mainView, StartScreen startScreen)
    {
        this.mainView = mainView;
        this.startScreen = startScreen;
    }
    
    /**
     * @return MainView A reference to the main view.
     */
    public MainView getMainView()
    {
        return this.mainView;
    }
    
    public StartScreen getStartScreen()
    {
        return this.startScreen;
    }
    
    /**
     * @return String The supplied text from the name input. Null if string empty.
     */
    public String getNameInput()
    {
        return this.startScreen.getNameInput();
    }
    
    /**
     * @return String The selected game type from the dropdown box on the start
     * screen.
     */
    public String getGameType()
    {
        return this.startScreen.getGameType();
    }
}
