package view.main;

/*
 * Main view class. Maintains references to all sub views.
 * 
 * @author Mathew Harrington
 * */

import java.awt.HeadlessException;
import java.awt.*;
import javax.swing.*;
//import view.MenuBar;

import view.screen.StartScreen;

@SuppressWarnings("serial")

public class MainView extends JFrame
{
    private MenuBar menuBar;
    private JPanel startScreen;

    public MainView() throws HeadlessException
    {
        // frame title
        super("Pocket Aces Poker");
        
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
        // e.g. this.startScreen = new StartScreen();
        this.startScreen = new StartScreen();
        this.getContentPane().add(startScreen, BorderLayout.CENTER);
        this.startScreen.setVisible(true);
        
        // set menu bar of main window with this
        this.setJMenuBar(menuBar);
    }
    
    /*
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

    /*
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
}
