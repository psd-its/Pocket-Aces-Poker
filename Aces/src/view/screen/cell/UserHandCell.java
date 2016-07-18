/**
 * Concrete user cell class, this is the cell that the user's current hand
 * will be displayed in.
 * 
 * @author Mathew Harrington
 */

package view.screen.cell;

import javax.swing.JLabel;
import model.facade.AcesFacade;
import model.player.Player;

public class UserHandCell extends AbsCell
{
    private Player player;
    JLabel hand;
    
    public UserHandCell(AcesFacade facade, Player player)
    {
        super(facade);
        this.player = player;
    }

    @Override
    public void refresh()
    {
        // only draw cards if user has a hand
        if(this.player.getHand()[0] != null)
        {
            drawCards();
        }  
    }
    
    /**
     * Draws the user's current hand.
     */
    private void drawCards()
    {
        // TODO need individual card images
        hand = new JLabel(player.getHand().toString());
        this.add(hand);
    }
}
