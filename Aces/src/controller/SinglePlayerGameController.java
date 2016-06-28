/**
 * Concrete controller class for starting a new single player game.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import model.facade.AcesFacade;
import model.game.Game;
import model.game.texas.TexasPoker;
import model.player.HumanPlayer;
import model.player.Player;
import model.table.Table;
import model.table.TexasTable;
import view.main.MainView;
import view.screen.StartScreen;
import test_harness.Harness;

public class SinglePlayerGameController extends AbsNewGameController
{   
    /**
     * Constructor, just calls super for now.
     * 
     * @param MainView A reference to the main view, sent to superclass 
     * constructor.
     */
    public SinglePlayerGameController(MainView mainView, StartScreen startScreen, AcesFacade facade)
    {
        super(mainView, startScreen, facade);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            // init single player game
            case StartScreen.SINGLE_PLAYER_BUTTON :
                // init table
                Table table = new TexasTable();
                // init game
                Game texasGame = new TexasPoker();
                // add game to table
                texasGame.addTable(table);
                // add game to facade
                super.createGame(texasGame);
                // init player
                Player newPlayer = new HumanPlayer(super.getNameInput());
                // add player to game
                super.addPlayer(newPlayer);
                // switch screen
                super.switchScreen(MainView.SINGLE_PLAYER_SCREEN);
                break;
            
            default :
                break;     
        }
    }
}
