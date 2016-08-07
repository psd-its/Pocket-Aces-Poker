/**
 * Concrete controller class for starting a new game.
 * All new games (we only have texas hold 'em right now) can
 * come through this controller, we can determine game type
 * and if it's sing/multiplayer and go from there.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observer;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import model.facade.AcesFacade;
import model.game.Game;
import model.game.texas.TexasPoker;
import model.player.HumanPlayer;
import model.player.Player;
import model.table.Table;
import model.table.TexasTable;
import server.PokerClient;
import server.ServerConst;
import view.main.MainView;
import view.screen.StartScreen;

public class NewGameSetupController extends AbsNewGameController
{
    private MainView mainView;

    /**
     * Constructor, just calls super for now.
     * 
     * @param MainView
     *            A reference to the main view, sent to superclass constructor.
     * @param StartScreen
     *            A reference to the game's start screen.
     * @param AcesFacade
     *            A reference to the model facade.
     */
    public NewGameSetupController(MainView mainView, StartScreen startScreen,
            AcesFacade facade)
    {
        super(mainView, startScreen, facade);
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
        // init single player games
            case StartScreen.SINGLE_PLAYER_BUTTON:

                // added this check to make it easier to add more games
                if (super.getGameType().equals("Texas Hold'Em Poker"))
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
                    super.addPlayer(newPlayer,
                            ((Observer) mainView.getGameScreen()));
                    // switch screen
                    super.switchScreen(MainView.SINGLE_PLAYER_TEXAS_SCREEN);
                }
                break;

            case StartScreen.MULTIPLAYER_BUTTON:

                // Create the player
                Player newPlayer = new HumanPlayer(super.getNameInput());
                // instantiate the client
                PokerClient client = new PokerClient(newPlayer);
                // join the server
                client.join();
                // switch screen
                super.switchScreen(MainView.SINGLE_PLAYER_TEXAS_SCREEN);

                break;

            default:
                break;
        }
    }
}
