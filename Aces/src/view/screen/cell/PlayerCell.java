/**
 * Concrete player cell class. 9 of these will be distributed around the game
 * screen, each representing a player (not the user). The cell will display
 * the players name, balance and their current status.
 * 
 * @author Mathew Harrington
 */
package view.screen.cell;

import java.awt.GridBagConstraints;

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
        
        GridBagConstraints c = new GridBagConstraints();
        
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
    
    /**
     * Draws to card backs if the player has a hand.
     */
    public void drawCards()
    {
        JLabel cardImage1, cardImage2;
        ImageIcon cardback = new ImageIcon("src/assets/CARDBACK.png");
        
        cardImage1 = new JLabel();
        cardImage2 = new JLabel();
        
        cardImage1.setIcon(cardback);
        cardImage2.setIcon(cardback);
        
        this.add(cardImage1);
        this.add(cardImage2);
    }
    
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
}
