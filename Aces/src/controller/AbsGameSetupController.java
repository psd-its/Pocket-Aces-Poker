/**
 * Abstract game setup controller, when user has entered all required information
 * on new game screen (we only have a new texas game screen right now) the game
 * specific controller will be responsoble for the remainder of game setup
 * and transfer of control to the actual game screen.
 * 
 * I laid it out this way so if we wanted to add say a blackjack game we'd just
 * create a SingleBlackjackController/MultiBlackjackController and they would
 * inherit from this base class.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.facade.AcesFacade;
import view.main.MainView;
import view.screen.AbsGameScreen;

public abstract class AbsGameSetupController implements ActionListener
{   
    private MainView mainView;
    private AcesFacade facade;
    protected AbsGameScreen gameSetupScreen;
    
    public AbsGameSetupController(AbsGameScreen gameSetupScreen)
    {
        this.gameSetupScreen = gameSetupScreen;
        this.mainView = gameSetupScreen.getMainView();
        this.facade= gameSetupScreen.getFacade();
    }
    
    /**
     * @return MainView A reference to the main view.
     */
    public MainView getMainView()
    {
        return this.mainView;
    }
    
    /**
     * @return AcesFacade A reference to the main view.
     */
    public AcesFacade getFacade()
    {
        return this.facade;
    }
    
    /**
     * @return AbsGameScreen A reference to the game setup screen (the previous
     * screen).
     */
    public AbsGameScreen getSetupScreen()
    {
        return this.gameSetupScreen;
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);

}
