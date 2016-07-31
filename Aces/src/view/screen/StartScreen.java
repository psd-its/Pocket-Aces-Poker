/**
 * Start Screen class. A JPanel that will be placed inside the main view.
 * 
 * If anyone wants to help with the game screen panels there's a great intro
 * to GridBagLayout and its constraints here: https://www.youtube.com/watch?v=ZipG38DJJK8
 * 
 * I chose GridBagLayout because I hadn't used it and it's considered to be
 * the most complex layout manager so thought it'd be cool to learn.
 * 
 * @author Mathew Harrington
 */

package view.screen;

import java.awt.Insets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import controller.NewGameSetupController;
import model.facade.AcesFacade;
import view.main.MainView;

public class StartScreen extends AbsGameScreen
{
    // these strings will be referenced by the controllers
    public static final String SINGLE_PLAYER_BUTTON = "Single-Player";
    public static final String MULTIPLAYER_BUTTON = "Multiplayer";
    
    private String[] gameTypes = { "Texas Hold'Em Poker" };
    private String selectGameText = "Select Game:";
    private String nameInputText = "Enter Your Name:";
    private String titleText = "Aces High";
    private JComboBox<String> selectGameDropdown;
    private JTextArea nameInputTextArea;
    private JLabel selectGameLabel; 
    private JLabel nameInputLabel; 
    private JLabel title;
    private JButton singlePlayerButton;
    private JButton multiplayerGameButton;
    
    /**
     * Constructor for start screen. Loads all the buttons and their labels, 
     * adds action listeners and performs some styling.
     * 
     * @param MainView A reference to the main view.
     * @param AcesFacade A reference to the model facade.
     */
    public StartScreen(MainView mainView, AcesFacade facade)
    {
        super(mainView, facade);
        
        // this will be default for all panels
        this.setVisible(false);
    }
    
    /**
     * TODO Might need some input validation here.
     */
    public String getNameInput()
    {
        String[] name = this.nameInputTextArea.getText()
                                    .split("\\p{javaSpaceChar}+");
        
        Pattern p = Pattern.compile("^[A-Za-z]{1,10}$");
        /* Creates a pattern to match "name" against, not case sensitive and
         * and must contain at least 1 character (making sure 'not empty'
         */
        Matcher m = p.matcher(name[0]);
        // Matches input 'name' against pattern exceptions
        
        if (!m.find())
        {
            nameInputTextArea.setText("");
            getNameInput();
        }
        return name[0];

    }
    
    /**
     * As we have hardcoded the game types into the dropdown there shouldn't
     * be any risk of returning null here. Again for this reason no validation 
     * required.
     * 
     * @return String The selected value from the game type dropdown.
     */
    public String getGameType()
    {
        String gameType = String.valueOf(this.selectGameDropdown.getSelectedItem());
        
        return gameType;
    }

    @Override
    public void load()
    {
        // configure and add title label to panel
        this.title = new JLabel(titleText);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        title.setFont(title.getFont().deriveFont(64.0f));
        this.add(title, constraints);
        
        // configure and add select game label to panel
        this.selectGameLabel = new JLabel(selectGameText);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(selectGameLabel, constraints);
        
        // configure and add select game dropdown to panel
        this.selectGameDropdown = new JComboBox<>(gameTypes);
        this.selectGameDropdown.setSelectedIndex(0);
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(selectGameDropdown, constraints);

        // configure and add name input label to panel
        this.nameInputLabel = new JLabel(nameInputText);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(nameInputLabel, constraints);
        
        // configure and add name text input to panel
        this.nameInputTextArea = new JTextArea(1, 15); // rows and columns
        this.nameInputTextArea.setMargin(new Insets(3,5,3,5)); // some padding
        constraints.gridx = 3;
        constraints.gridy = 2;
        this.add(nameInputTextArea, constraints);
        
        // configure and add single player button to panel
        this.singlePlayerButton = new JButton(SINGLE_PLAYER_BUTTON);
        this.singlePlayerButton.addActionListener(new NewGameSetupController(mainView, this, facade));
        constraints.gridx = 2;
        constraints.gridy = 3;
        this.add(singlePlayerButton, constraints);
        
        // configure and add multiplayer button to panel
        this.multiplayerGameButton = new JButton(MULTIPLAYER_BUTTON);
        this.multiplayerGameButton.addActionListener(new NewGameSetupController(mainView, this, facade));
        constraints.gridx = 2;
        constraints.gridy = 4;
        this.add(multiplayerGameButton, constraints);
    }
}
