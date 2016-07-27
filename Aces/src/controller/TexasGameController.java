package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.facade.AcesFacade;

public class TexasGameController implements ActionListener
{
    private AcesFacade model;
    public TexasGameController(AcesFacade model)
    {
        // TODO Auto-generated constructor stub
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub
        switch(e.getActionCommand())
        {
            case "foldButton":
                
                
        }
        
    }

}
