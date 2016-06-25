/**
 * Menu Bar Controller class. Handles all the events coming from the menu bar 
 * at the top of the window. The new game options will fire the same combination 
 * of calls as when a user clicks the the game button(s) inside the window. 
 * 
 * @author Mathew Harrington
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.main.MenuBar;
import view.main.MainView;

public class MenuBarController implements ActionListener
{
    private MainView mainView;
    private String aboutMessage = "Pocket Aces Poker: Game built for Building IT Systems, SP2 2016.\n\n"
            + "Team consisting of: \nTristan McSwain\nTaylor Curr\nNanuel Ortiz\nNicholas Henderson\nMathew Harrington";
    
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
                
            // brief description of application
            case MenuBar.ABOUT_OPTION :
                JOptionPane.showMessageDialog(mainView, aboutMessage);
                break;
                
            // TODO begin new single player game 
            case MenuBar.NEW_SINGLE_PLAYER_GAME_OPTION :
                break;
                
            // TODO begin new multi player game - go to the 'host game' screen?
            case MenuBar.NEW_MULTI_PLAYER_GAME_OPTION :
                break;
                
            // TODO display readme - still needs to be written (more in depth than the 'about' message)
            case MenuBar.README_OPTION :
                break;
        }
    }
}
