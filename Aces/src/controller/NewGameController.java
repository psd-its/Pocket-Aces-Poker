/**
 * Concrete controller class for starting a new game.
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

public class NewGameController extends AbsNewGameController
{   
    /**
     * Constructor, just calls super for now.
     * 
     * @param MainView A reference to the main view, sent to superclass 
     * constructor.
     * @param StartScreen A reference to the game's start screen.
     * @param AcesFacade A reference to the model facade.
     */
    public NewGameController(MainView mainView, StartScreen startScreen, AcesFacade facade)
    {
        super(mainView, startScreen, facade);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            // init single player games
            case StartScreen.SINGLE_PLAYER_BUTTON :
                
                // added this check to make it easier to add more games
                if(super.getGameType().equals("Texas Hold'Em Poker"))
                {
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
                    super.switchScreen(MainView.SINGLE_PLAYER_TEXAS_SCREEN);
                }
                break;
                
            case StartScreen.MULTIPLAYER_BUTTON :
                break;
            
            default :
                break;     
        }
    }
}
