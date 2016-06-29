/**
 * Concrete Single Player Screen class, represents the configuration screen 
 * for a new single player game. Contains screen specific variables.
 * 
 * @author Mathew Harrington 
 */


package view.screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.facade.AcesFacade;
import view.main.MainView;

public class SinglePlayerScreen extends AbsGameScreen
{
    public static final String START_BUTTON = "Start";
    
    // labels, buttons etc
    private JLabel title;
    private JLabel welcomeMessage;
    private JLabel numPlayers;
    private JComboBox<Integer> numPlayersDropdown;
    private JButton startButton;
    private JButton backButton;
    
    // text for labels and buttons
    private String titleText = "Single Player";
    private String numPlayersText = "Number of AI Players (Max 9)";
    private String welcomeMessageText;
    private String backButtonText = "Back";
    
    // not sure about hardcoding this
    private Integer[] numPlayersList = { 1,2,3,4,5,6,7,8,9 };
    
    /**
     * Constructor for single player screen.
     * 
     * @param MainView A reference to the main view window.
     * @param AcesFacade A reference to the model facade.
     */
    public SinglePlayerScreen(MainView mainView, AcesFacade facade)
    {
        super(mainView, facade);
        this.setVisible(false);
    }
    
    /**
     * Method to perform the actions we couldn't do in constructor because
     * player object hadn't been created. Call this method before setting
     * visibility to true.
     */
    public void load()
    {
        // configure and add title label to panel
        this.title = new JLabel(titleText);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        title.setFont(title.getFont().deriveFont(64.0f));
        this.add(title, constraints);
        
        setWelcomeText();
        this.welcomeMessage = new JLabel(welcomeMessageText);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        title.setFont(title.getFont().deriveFont(50.0f));
        this.add(welcomeMessage, constraints);
    }
    
    /**
     * Simply loads the welcome text with the name the user has entered. This
     * can't be called until user has filled out start screen inputs, so called
     * from load().
     */
    private void setWelcomeText()
    {
        this.welcomeMessageText = "Welcome ";
        this.welcomeMessageText += facade.getPlayer(0).getName();
        this.welcomeMessageText += ".\nHone your skills against 1-9 AI opponents.";
        
    }
}
