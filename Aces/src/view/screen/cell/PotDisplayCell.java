/**
 * Concrete class for the pot cell. Displays the current amount in the game's
 * pot.
 * 
 * @author Mathew Harrington
 */

package view.screen.cell;

import javax.swing.JLabel;
import model.facade.AcesFacade;

public class PotDisplayCell extends AbsCell
{
    private final String text = "Pot";
    private int potSize;
    private JLabel textLabel, potSizeLabel;

    public PotDisplayCell(AcesFacade facade, int potSize)
    {
        super(facade);
        this.potSize = potSize;
        this.textLabel = new JLabel(text);
        this.potSizeLabel = new JLabel(Integer.toString(potSize));
        
        this.add(textLabel);
        this.add(potSizeLabel);
    }

    @Override
    public void refresh()
    {
        // will need to get new pot size
        this.potSize = super.getFacade().getGame().getTable().getPot();
        this.potSizeLabel.setText(Integer.toString(this.potSize));
    }
}
