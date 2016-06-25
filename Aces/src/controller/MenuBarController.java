/**
 * @author Mathew Harrington
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MenuBar;
import view.main.MainView;

public class MenuBarController implements ActionListener
{
    private MainView mainView;
    
    public MenuBarController(MainView mainView)
    {
        this.mainView = mainView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case MenuBar.EXIT_OPTION :
                int option =
                JOptionPane.showConfirmDialog(mainView, "Sure you want to exit?");
                if (option == JOptionPane.YES_OPTION)
                    System.exit(0);
                break;
            case MenuBar.ABOUT_OPTION :
                break;
            case MenuBar.NEW_SINGLE_PLAYER_GAME_OPTION :
                break;
            case MenuBar.NEW_MULTI_PLAYER_GAME_OPTION :
                break;
            case MenuBar.README_OPTION :
                break;
        }
    }
}
