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
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.facade.AcesFacade;

public abstract class AbsCell extends JPanel
{
    private AcesFacade facade;
    
    public AbsCell(AcesFacade facade)
    {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.facade = facade;
    }
    
    public AcesFacade getFacade()
    {
        return this.facade;
    }
    
    public abstract void refresh();
}
