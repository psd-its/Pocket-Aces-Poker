/**
 * Controller class for handling the buttons in the game screen. There's four
 * buttons the user can interact with at this stage. After each action might
 * need to do a refresh of the game screen, or should we call that after each 
 * round?
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.facade.AcesFacade;
import model.player.Player;
import view.main.MainView;
import view.screen.PokerGameScreen;

public class PokerGameScreenController implements ActionListener
{
    private AcesFacade facade;
    private PokerGameScreen gameScreen;
    private MainView mainView;
    private Player user;
    
    private static final String EXIT_COMMAND = "exit", FOLD_COMMAND = "fold",
                                CALL_COMMAND = "call", RAISE_COMMAND = "raise $";
    
    public PokerGameScreenController(AcesFacade facade, PokerGameScreen gameScreen, MainView mainView)
    {
        this.facade = facade;
        this.gameScreen = gameScreen;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // handle game exit
        if(e.getActionCommand().equals(PokerGameScreenController.EXIT_COMMAND))
        {
            System.out.println("exit command \n");
            
            // might need to do some sort of 'end game' process here
            this.mainView.switchScreen(this.gameScreen, MainView.START_SCREEN);
        }
        
        // handle user fold
        if(e.getActionCommand().equals(PokerGameScreenController.FOLD_COMMAND))
        {
            System.out.println("fold command \n");
            this.user.fold();
        }
        
        // handle user call
        if(e.getActionCommand().equals(PokerGameScreenController.CALL_COMMAND))
        {
            System.out.println("call command \n");
            this.user.call(this.facade.getGame().getTable());
        }
        
        // handle user raise
        if(e.getActionCommand().equals(PokerGameScreenController.RAISE_COMMAND))
        {
            System.out.println("raise command \n");
            
            // will need to get the input from text area and parse as integer
            // TODO input validation here
            String raiseInput = gameScreen.getRaiseInput().getText();
            
            if(raiseInput != null && !raiseInput.isEmpty())
            {
                int raiseAmount = Integer.parseInt(raiseInput);
                this.user.raise(this.facade.getGame().getTable(), raiseAmount);
            }
        }
    }
    
    public void setUser(Player user)
    {
        this.user = user;
    }
}
