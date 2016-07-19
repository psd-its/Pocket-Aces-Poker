/**
 * Controller class for handling the buttons in the game screen.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.facade.AcesFacade;
import view.main.MainView;
import view.screen.PokerGameScreen;

public class PokerGameScreenController implements ActionListener
{
    private AcesFacade facade;
    private PokerGameScreen gameScreen;
    private MainView mainView;
    
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
        }
        
        // handle user fold
        if(e.getActionCommand().equals(PokerGameScreenController.FOLD_COMMAND))
        {
            System.out.println("fold command \n");
        }
        
        // handle user call
        if(e.getActionCommand().equals(PokerGameScreenController.CALL_COMMAND))
        {
            System.out.println("call command \n");
        }
        
        // handle user raise
        if(e.getActionCommand().equals(PokerGameScreenController.RAISE_COMMAND))
        {
            System.out.println("raise command \n");
        }
    }

}
