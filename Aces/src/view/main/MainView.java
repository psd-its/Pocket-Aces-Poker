package view.main;

/**
 * Main view class. Maintains references to all sub views.
 * 
 * @author Mathew Harrington
 */

import java.awt.HeadlessException;
import java.awt.*;
import javax.swing.*;
import model.facade.AcesFacade;
import view.screen.SinglePlayerScreen;
import view.screen.StartScreen;

@SuppressWarnings("serial")

public class MainView extends JFrame
{
    private AcesFacade acesFacade;
    private MenuBar menuBar;
    private StartScreen startScreen;
    private SinglePlayerScreen singlePlayerScreen;
    
    // these are used for swaping out screens in switchScreen()
    public static final String START_SCREEN = "startScreen";
    public static final String SINGLE_PLAYER_SCREEN = "singlePlayerScreen";

    /**
     * Constructor for the main view. Sets up the window's title, look and feel, 
     * window size and loads all of the sub views.
     */
    public MainView(AcesFacade facade) throws HeadlessException
    {
        // frame title
        super("Pocket Aces Poker");
        
        // set facade
        this.acesFacade = facade;
        
        // set look and feel
        this.setLookAndFeel();
        
        // main window size
        this.setSize(800, 600);
        
        // main window location
        this.setWindowLocation();

        // set default close behavior
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialize sub views
        this.menuBar = new MenuBar(this);
        
        // load each screen
        this.startScreen = new StartScreen(this, facade);
        this.singlePlayerScreen = new SinglePlayerScreen(this, facade);
        
        // set start screen to visible
        this.getContentPane().add(startScreen, BorderLayout.CENTER);
        //this.getContentPane().add(singlePlayerScreen, BorderLayout.CENTER);
        
        this.startScreen.setVisible(true);
        
        // set menu bar of main window with this
        this.setJMenuBar(menuBar);
    }
    
    /**
     * Sets main window frame location to center of screen.
     */
    private void setWindowLocation()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth / 4, screenHeight / 4);
    }

    /**
     * Sets applications 'look and feel' to suit the system it's running on. 
     */
    private void setLookAndFeel()
    {
        try
        {
            // set look and feel of system program is running on
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            System.err.println("Couldn't find class for specified look and feel");
            System.err.println("Using the default look and feel.");
        } 
        
        catch (UnsupportedLookAndFeelException e) 
        {
            System.err.println("Can't use the specified look and feel on this platform");
            System.err.println("Using the default look and feel.");
        } 
        
        catch (Exception e) 
        {
            System.err.println("Couldn't get specified look and feel, for some reason.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }
    }
    
    /**
     * Method to switch from one screen to another. Default behavior, if 
     * something isn't right with the request is to load the start screen,
     * we can modify this behavior later if needed.
     * 
     * @param JPanel The current screen the application is running on.
     * @param String A string representing the screen we want to swap to.
     */
    public void switchScreen(JPanel currentScreen, String requestedScreen)
    {
        switch(requestedScreen)
        {
            case MainView.START_SCREEN :
                currentScreen.setVisible(false);
                this.startScreen.setVisible(true);
                break;
            
            case MainView.SINGLE_PLAYER_SCREEN :
                this.singlePlayerScreen.load();
                currentScreen.setVisible(false);
                this.getContentPane().add(singlePlayerScreen, BorderLayout.CENTER);
                this.singlePlayerScreen.setVisible(true);
                break;
            
            default :
                currentScreen.setVisible(false);
                this.startScreen.setVisible(true);
                break;
        }
    }
    
    /**
     * @return AcesFacade A reference to the facade for the model.
     */
    public AcesFacade getFacade()
    {
        return this.acesFacade;
    }
}
