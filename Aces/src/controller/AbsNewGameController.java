/**
 * Abstract controller class for starting a new game.
 * 
 * @author Mathew Harrington
 **/

package controller;

import java.awt.event.ActionListener;
import model.facade.AcesFacade;
import model.game.Game;
import model.player.Player;
import model.table.TableFull;
import view.main.MainView;
import view.screen.StartScreen;

public abstract class AbsNewGameController implements ActionListener
{
    private MainView mainView;
    private StartScreen startScreen;
    private AcesFacade facade;
    
    /**
     * Base constructor, allocates the main view reference.
     * 
     * @param MainView A reference to the main window of the application.
     */
    public AbsNewGameController(MainView mainView, StartScreen startScreen, AcesFacade facade)
    {
        this.mainView = mainView;
        this.startScreen = startScreen;
        this.facade = facade;
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
    
    /**
     * @param Game A reference to the game.
     */
    public void createGame(Game game)
    {
        this.facade.createGame(game);
    }
    
    /**
     * @param Player The player to add to the game.
     */
    public void addPlayer(Player player)
    {
        try
        {
            this.facade.addPlayer(player);
        }
        catch (TableFull e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * @param String The requested screen to display.
     */
    public void switchScreen(String requestedScreen)
    {
        this.mainView.switchScreen(this.startScreen, requestedScreen);
    }
}
