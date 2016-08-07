package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.facade.AcesFacade;
/**
 * @deprecated
 */
public class PlayerScreenController implements ActionListener
{
    private AcesFacade facade;
    public PlayerScreenController(AcesFacade facade)
    {
        // TODO Auto-generated constructor stub
        this.facade = facade;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub

    }

}
