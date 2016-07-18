/**
 * Concrete player cell class. 9 of these will be distributed around the game
 * screen, each representing a player (not the user). The cell will display
 * the players name, balance and their current status.
 * 
 * @author Mathew Harrington
 */
package view.screen.cell;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.facade.AcesFacade;
import model.player.Player;

public class PlayerCell extends AbsCell
{
    private JLabel nameLabel, statusLabel, balanceLabel;
    private Player player;
    
    public PlayerCell(AcesFacade facade, Player player)
    {
        super(facade);
        
        this.player = player;
        
        this.nameLabel = new JLabel(player.getName());
        this.balanceLabel = new JLabel(Integer.toString(player.getBalance()));
        
        this.add(nameLabel);
        this.add(balanceLabel);
    }

    @Override
    public void refresh()
    {
        // write labels
        this.balanceLabel.setText(Integer.toString(player.getBalance()));
        //this.statusLabel.setText(status);
        
        // only draw cards if player has a hand
        if(this.player.getHand()[0] != null)
        {
            drawCards();
        }     
    }
    
    public void drawCards()
    {
        JLabel cards;
        ImageIcon cardback = new ImageIcon("src/assets/cardback.png");
        
        cards = new JLabel();
        cards.setIcon(cardback);
        
        this.add(cards);
    }
    
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
}
