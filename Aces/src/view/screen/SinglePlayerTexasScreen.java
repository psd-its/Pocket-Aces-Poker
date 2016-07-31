/**
 * Concrete Single Player Screen class, represents the configuration screen 
 * for a new single player game. Contains screen specific variables.
 * 
 * @author Mathew Harrington 
 */


package view.screen;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import controller.SingleTexasSetupController;
import model.facade.AcesFacade;
import view.main.MainView;

public class SinglePlayerTexasScreen extends AbsGameScreen
{
    // controller for screen
    private SingleTexasSetupController controller;
    
    // button text - will be reference from the controller
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
    private String numPlayersText = "Select number of AI Players (Maximum 9)";
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
    public SinglePlayerTexasScreen(MainView mainView, AcesFacade facade)
    {
        super(mainView, facade);
        this.setVisible(false);
        this.controller = new SingleTexasSetupController(this);
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
        constraints.weighty = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.NORTH;
        title.setFont(title.getFont().deriveFont(64.0f));
        this.add(title, constraints);
        
        setWelcomeText();
        
        // configure and add welcome massage to screen
        this.welcomeMessage = new JLabel(welcomeMessageText);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        welcomeMessage.setFont(title.getFont().deriveFont(27.0f));
        this.add(welcomeMessage, constraints);
        
        // number of players message
        this.numPlayers = new JLabel(numPlayersText);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        numPlayers.setFont(title.getFont().deriveFont(20.0f));
        this.add(numPlayers, constraints);
        
        // combo box
        this.numPlayersDropdown = new JComboBox<Integer>(numPlayersList);
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        numPlayersDropdown.setFont(title.getFont().deriveFont(15.0f));
        this.add(numPlayersDropdown, constraints);
        
        // start button
        this.startButton = new JButton(START_BUTTON);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.insets = new Insets(15, 15, 50, 15);
        startButton.setFont(title.getFont().deriveFont(20.0f));
        startButton.addActionListener(controller);
        this.add(startButton, constraints);
    }
    
    /**
     * Simply loads the welcome text with the name the user has entered. This
     * can't be called until user has filled out start screen inputs, so called
     * from load(). Text is wrapped in html tags so we can use the break tag.
     */
    private void setWelcomeText()
    {
        this.welcomeMessageText = "<html><div style='text-align: center;'>Welcome ";
        this.welcomeMessageText += facade.getPlayer(0).getName();
        this.welcomeMessageText += "<br>Hone your skills against 1-9 AI opponents.</html>";   
    }
    
    /**
     *@return Integer The number of AI players selected.
     */
    public int getNumCompPlayers()
    {
        // this is awkward. The +1 is needed because indexes start at 0
        return Integer.valueOf(this.numPlayersDropdown.getSelectedIndex()) + 1;
    }
}
