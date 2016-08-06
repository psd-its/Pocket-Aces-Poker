/**
 * Abstract cell class, keeps a reference to the facade. Extends JPanel and
 * is designed to be the base class for all other cell classes. The 'cells' 
 * are the different areas in the game screen grid, the ones that will have 
 * their content updated.
 * 
 * @author Mathew Harrington
 */
package view.screen.cell;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.card.CardMapper;
import model.facade.AcesFacade;

public abstract class AbsCell extends JPanel
{
    private AcesFacade facade;
    protected CardMapper mapper;
    
    public AbsCell(AcesFacade facade)
    {
        super(new GridBagLayout());
        //this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.facade = facade;
        this.mapper = new CardMapper();
    }
    
    public AcesFacade getFacade()
    {
        return this.facade;
    }

    public abstract void refresh();
}
