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
import model.game.RTM;
import model.game.texas.TexasGameWorker;
import model.game.texas.TexasPoker;
import view.screen.AbsGameScreen;
import view.screen.SinglePlayerTexasScreen;
import view.screen.StartScreen;
import view.screen.PokerGameScreen;

@SuppressWarnings("serial")

public class MainView extends JFrame
{
    private AcesFacade acesFacade;
    private AbsGameScreen startScreen, singlePlayerScreen, texasGameScreen;
    
    // these are used for swaping out screens in switchScreen()
    public static final String START_SCREEN = "startScreen";
    public static final String SINGLE_PLAYER_TEXAS_SCREEN = "singlePlayerTexasScreen";
    public static final String TEXAS_GAME_SCREEN = "texasGameScreeen";

    /**
     * Constructor for the main view. Sets up the window's title, look and feel, 
     * window size and loads all of the sub views.
     * 
     * @param AcesFacade A reference to the model facade object.
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
        this.setSize(1400, 1050);
        
        // main window location
        this.setWindowLocation();

        // set default close behavior
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialize sub views
        this.loadScreens();
        
        // set start screen to visible
        this.getContentPane().add(startScreen, BorderLayout.CENTER);
        this.startScreen.setVisible(true);
    }
    
    /**
     * Function to load each of the screens used in the application.
     */
    private void loadScreens()
    {
        this.startScreen = new StartScreen(this, acesFacade);
        this.startScreen.load(); // we only load the start screen at this time
        this.singlePlayerScreen = new SinglePlayerTexasScreen(this, acesFacade);
        this.texasGameScreen = new PokerGameScreen(this, acesFacade);
    }
    
    /**
     * Sets main window frame location to center of user's screen.
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
     * Sets application's 'look and feel' to suit the system it's running on. 
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
            
            case MainView.SINGLE_PLAYER_TEXAS_SCREEN :
                this.singlePlayerScreen.load();
                currentScreen.setVisible(false);
                this.getContentPane().add(singlePlayerScreen, BorderLayout.CENTER);
                this.singlePlayerScreen.setVisible(true);
                break;
                
            case MainView.TEXAS_GAME_SCREEN :
                this.texasGameScreen.load();
                currentScreen.setVisible(false);
                this.getContentPane().add(texasGameScreen, BorderLayout.CENTER);
                //this.texasGameScreen.setVisible(true);
                PokerGameScreen pokerScreen = (PokerGameScreen) this.texasGameScreen;
                pokerScreen.load();
                pokerScreen.setVisible(true);
                try
                {
                    TexasPoker game = (TexasPoker)this.acesFacade.getGame();
                    game.addObserver(pokerScreen);
                    //game.play();
                    TexasGameWorker gameWorker = new TexasGameWorker(game);
                    gameWorker.execute();
                }
                
                catch(Exception e)
                {
                    System.out.println("something exceptional happened...");
                    System.out.println(e.getMessage()); 
                    e.printStackTrace();
                }
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
    
    public AbsGameScreen getGameScreen()
    {
        return texasGameScreen;
    }
    
    // Added a setter for the facade to facilitate multi-player
    public void setFacade(AcesFacade facade)
    {
        this.acesFacade = facade;
    }
}
