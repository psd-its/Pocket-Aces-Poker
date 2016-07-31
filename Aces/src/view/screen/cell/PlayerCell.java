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

import model.card.CardMapper;
import model.facade.AcesFacade;
import model.player.Player;

public class PlayerCell extends AbsCell
{
    private JLabel nameLabel, statusLabel, balanceLabel;
    private Player player;
    private CardMapper mapper;
    
    public PlayerCell(AcesFacade facade, Player player)
    {
        super(facade);
        
        this.player = player;
        this.mapper = new CardMapper();
        
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
     * Draws to card backs if the player has a hand. Draw card front if player's
     * hand is visible.
     */
    public void drawCards()
    {
        JLabel cardImage1, cardImage2;
        ImageIcon card1, card2;
        String card1Path, card2Path;
        cardImage1 = new JLabel();
        cardImage2 = new JLabel();
        
        
        if(this.player.getHand()[0].isShowing() && this.player.getHand()[0] != null)
        {
            card1Path = mapper.map(this.player.getHand()[0].toString());
            card1 = new ImageIcon(card1Path);
            cardImage1.setIcon(card1);
        }
        
        else 
        {
            card1 = new ImageIcon("src/assets/CARDBACK.png");
            cardImage1.setIcon(card1);
        }
        
        if(this.player.getHand()[1].isShowing() && this.player.getHand()[1] != null)
        {
            card2Path = mapper.map(this.player.getHand()[1].toString());
            card2 = new ImageIcon(card2Path);
            cardImage2.setIcon(card2);
        }
        
        else 
        {
            card2 = new ImageIcon("src/assets/CARDBACK.png");
            cardImage2.setIcon(card2);
        }

        this.add(cardImage1);
        this.add(cardImage2);
    }
    
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
}
