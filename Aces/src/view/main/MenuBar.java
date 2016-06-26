package view.main;

/*
 * Class represents the menu bar that will be at the top of the main window.
 * We can add more menu items as needed. These call the same set of methods 
 * as when a user clicks one of the buttons within the screen.
 * 
 * @author Mathew Harrington
 */

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.MenuBarController;
import view.main.MainView;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{
    private MenuBarController controller;
    private JMenu file;
    private JMenu newOptions;
    private JMenu help;
    private JMenuItem menuItem;
    
    // menu heading strings
    public static final String FILE = "File";
    public static final String HELP = "Help";
    
    // menu item strings
    public static final String EXIT_OPTION = "Exit";
    public static final String ABOUT_OPTION = "About";
    public static final String NEW_OPTION = "New";
    public static final String NEW_SINGLE_PLAYER_GAME_OPTION = "New Single Player Game";
    public static final String NEW_MULTIPLAYER_GAME_OPTION = "New Multiplayer Game";
    public static final String README_OPTION = "Readme";
    
    /*
     * Constructor for the menu bar. Initializes its controller, configures
     * and adds menu items and finall adds the menu to the bar.
     * 
     * @param MainView A reference to the main window of the application.
     * */
    public MenuBar(MainView mainView)
    {
        super();
     
        this.controller = new MenuBarController(mainView);
        
        // file menu
        file = new JMenu(FILE);
        // sub menu
        newOptions = new JMenu(NEW_OPTION);
        menuItem = new JMenuItem(NEW_SINGLE_PLAYER_GAME_OPTION);
        menuItem.addActionListener(controller);
        newOptions.add(menuItem);
        menuItem = new JMenuItem(NEW_MULTIPLAYER_GAME_OPTION);
        menuItem.addActionListener(controller);
        newOptions.add(menuItem);        
        file.add(newOptions);
        
        // rest of menu items
        menuItem = new JMenuItem(ABOUT_OPTION);
        menuItem.addActionListener(controller);
        file.add(menuItem);
        menuItem = new JMenuItem(EXIT_OPTION);
        menuItem.addActionListener(controller);
        file.add(menuItem);
        
        // help menu
        help = new JMenu(HELP);
        menuItem = new JMenuItem(README_OPTION);
        menuItem.addActionListener(controller);
        help.add(menuItem);
        
        // add menus to menu bar
        this.add(file);
        this.add(help); 
    }
}
