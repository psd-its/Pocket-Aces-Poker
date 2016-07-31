/**
 * Concrete card display cell class, five of these cells will be in the center
 * of the game screen and will each display a card dealt by the dealer.
 * 
 * @author Mathew Harrington
 */

package view.screen.cell;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.card.CardMapper;
import model.facade.AcesFacade;
import model.table.Table;

public class CardDisplayCell extends AbsCell
{
    private JLabel card;
    private Table table;
    private CardMapper mapper;
    private int cardNum, cardIndex;
    
    public CardDisplayCell(AcesFacade facade, Table table, int cardNum)
    {
        super(facade);
        this.table = table;
        this.mapper = new CardMapper();
        this.card = new JLabel();
        this.cardNum = cardNum;
        this.cardIndex = this.cardNum - 1;
    }

    @Override
    public void refresh()
    {
        //String pathsep = File.pathSeparator;
        // dont try to draw if no card at index (hasn't been dealt yet)
        if(table.getCardsInPlay()[this.cardIndex] != null && table.getCardsInPlay()[this.cardIndex].isShowing())
        {
            drawCard(table.getCardsInPlay()[this.cardIndex].toString());
        }
        
        else 
        {
            String path = mapper.getCardbackLargePath();
            ImageIcon image = new ImageIcon(path);
            card.setIcon(image);
            this.add(card);
        }
    }
    
    /**
     * Draws a card based on the string given. We need the x2 size for these
     * images.
     * 
     * @param s The toString representation of the card.
     */
    private void drawCard(String s)
    {
        String path = mapper.map(s, "_2X");
        ImageIcon image = new ImageIcon(path);
        card.setIcon(image);
        this.add(card);
    }
}
