/**
 * Concrete player cell class. 9 of these will be distributed around the game
 * screen, each representing a player (not the user). The cell will display
 * the players name, balance and their current status.
 * 
 * @author Mathew Harrington
 */
package view.screen.cell;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.card.CardMapper;
import model.facade.AcesFacade;
import model.player.Player;

public class PlayerCell extends AbsCell
{
    private Player player;
    private CardMapper mapper;
    
    public PlayerCell(AcesFacade facade, Player player)
    {
        super(facade);
        
        this.player = player;
        this.mapper = new CardMapper();
    }

    @Override
    public void refresh()
    {
        // clear the container
        this.removeAll();
        
        // write labels
        drawPlayerInfo();
        //this.statusLabel.setText(status);
        
        // only draw cards if player has a hand
        if(this.player.getHand()[0] != null)
        {
            drawCards();
        }     
    }
    
    /**
     * Draws player's info, their name and balance.
     */
    private void drawPlayerInfo()
    {
        // building the label, used html to add some basic styling
        String nameBalanceText = "<html><div style='text-align: center;'>" + 
                                 this.player.getName() + "<br/>" + "$" + 
                                 Integer.toString(this.player.getBalance()) + 
                                 "</div></html>";
        JLabel nameBalanceLabel = new JLabel(nameBalanceText);
        nameBalanceLabel.setForeground(Color.LIGHT_GRAY);
        nameBalanceLabel.setFont(nameBalanceLabel.getFont().deriveFont(18.0f));
        
        // adding some internal padding
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 20;
        c.ipady = 20;
        
        this.add(nameBalanceLabel, c);
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
            card1 = new ImageIcon(this.mapper.getCardbackPath());
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
            card2 = new ImageIcon(this.mapper.getCardbackPath());
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
