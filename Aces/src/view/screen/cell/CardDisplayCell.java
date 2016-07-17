/**
 * Concrete card display cell class, five of these cells will be in the center
 * of the game screen and will each display a card dealt by the dealer.
 * 
 * @author Mathew Harrington
 */

package view.screen.cell;

import model.facade.AcesFacade;

public class CardDisplayCell extends AbsCell
{
    public CardDisplayCell(AcesFacade facade)
    {
        super(facade);
    }

    @Override
    public void refresh()
    {
        // TODO Auto-generated method stub
        
    }
}
