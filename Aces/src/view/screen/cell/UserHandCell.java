/**
 * Concrete user cell class, this is the cell that the user's current hand
 * will be displayed in.
 * 
 * @author Mathew Harrington
 */

package view.screen.cell;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.card.CardMapper;
import model.facade.AcesFacade;
import model.player.Player;

public class UserHandCell extends AbsCell
{
    private Player player;
    //private CardMapper mapper;
    
    public UserHandCell(AcesFacade facade, Player player)
    {
        super(facade);
        this.player = player;
        //this.mapper = new CardMapper();
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
        String card1Path, card2Path;
        JLabel card1Label, card2Label;
        ImageIcon card1Image, card2Image;
        
        card1Path = mapper.map(this.player.getHand()[0].toString());
        card2Path = mapper.map(this.player.getHand()[1].toString());
        
        card1Image = new ImageIcon(card1Path);
        card2Image = new ImageIcon(card2Path);
        
        card1Label = new JLabel();
        card2Label = new JLabel();
        
        card1Label.setIcon(card1Image);
        card2Label.setIcon(card2Image);
        
        this.removeAll();
        
        System.out.println(card1Path);
        System.out.println(card2Path);
        
        this.add(card1Label);
        this.add(card2Label);
    }
}
