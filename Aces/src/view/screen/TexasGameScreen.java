/**
 * TexasGameScreen, the game playing screen for a texas hold'em game. I'm 
 * thinking that we can divide the screen into 11 zones (one for the dealer/deck)
 * in a sort of grid structure.
 * 
 * @author Mathew Harrington
 */

package view.screen;

import model.facade.AcesFacade;
import model.player.Player;
import view.main.MainView;

public class TexasGameScreen extends AbsGameScreen
{
    /**
     * TexasGameScreen constructor, sets visibility to false, the default for
     * all screens. Divides the window into zones.
     * 
     * @param MainView A reference to the main view.
     * @param Facade A reference to the aces facade.
     */
    public TexasGameScreen(MainView mainView, AcesFacade facade)
    {
        super(mainView, facade);
        this.setVisible(false);
        
        // divide into 11 zones (dealer/deck in center?)
        
    }

    @Override
    public void load()
    {
        // need to fill a player array with game's players
        Player[] players = super.getFacade().getGame().getPlayers();
    }

}
