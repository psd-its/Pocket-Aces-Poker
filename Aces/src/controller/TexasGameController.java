package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.facade.AcesFacade;
import model.player.HumanPlayer;
/**
 * @deprecated
 * @author deep_thought
 *
 */
public class TexasGameController implements ActionListener
{
    /**
     * The controller for each players screen
     * Each human player will have one instance of 
     * this attached to each of there buttons
     */
    private AcesFacade model;
    private HumanPlayer p;
    public TexasGameController(AcesFacade model, int seatIndex) throws Exception
    {
        // TODO Auto-generated constructor stub
        this.model = model;
        this.p = (HumanPlayer)model.getPlayer(seatIndex);
        if (p == null) 
            throw new Exception("There is not a valid player at that seat!");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub
        switch(e.getActionCommand())
        {
            case "foldButton":
                p.fold();
                p.cancel();
                break;
            case "checkButton":
                if (p.check(model.getGame().getTable()))
                {
                    p.cancel();
                    break;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You cannot check");
                    break;
                }
            case "callButton":
                break;
            case "raiseButton":
                break;
            case "exitButton":
                break;
                
                
                
        }
        
    }

}
