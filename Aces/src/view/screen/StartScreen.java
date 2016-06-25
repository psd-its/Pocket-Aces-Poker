/*
 * Start Screen class. A JPanel that will be placed inside the main view.
 * 
 * If anyone wants to help with the game screen panels there's a great intro
 * to GridBagLayout and its constraints here: https://www.youtube.com/watch?v=ZipG38DJJK8
 * 
 * I chose GridBagLayout because I hadn't used it and it's considered to be
 * the most complex layout manager so thought it'd be cool to learn.
 * 
 * @author Mathew Harrington
 * */

package view.screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StartScreen extends JPanel
{
    private String selectGameText = "Select Game:";
    private String nameInputText = "Enter Your Name:";
    private String singlePlayerButtonText = "Single-Player";
    private String multiplayerButtonText = "Multiplayer";
    private String titleText = "Aces High";
    private String[] gameTypes = { "Texas Hold'Em Poker" };
    //private boolean isVisible = false; // this might be redundant
    private JComboBox<String> selectGameDropdown;
    private JTextArea nameInputTextArea;
    private JLabel selectGameLabel; 
    private JLabel nameInputLabel; 
    private JLabel title;
    private JButton singlePlayerButton;
    private JButton multiplayerGameButton;
    private GridBagConstraints constraints;
    
    public StartScreen()
    {
        super(new GridBagLayout());
        this.constraints = new GridBagConstraints();
        constraints.insets = new Insets(15,15,15,15);
        
        this.setVisible(false);
        
        // configure and add title label to panel
        this.title = new JLabel(titleText);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        title.setFont (title.getFont().deriveFont(64.0f));
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
        this.nameInputTextArea = new JTextArea();
        this.nameInputTextArea.setColumns(15); // wont show up if this not set
        //constraints.gridwidth = 2;
        constraints.gridx = 3;
        constraints.gridy = 2;
        this.add(nameInputTextArea, constraints);
        
        // configure and add single player button to panel
        this.singlePlayerButton = new JButton(singlePlayerButtonText);
        constraints.gridx = 2;
        constraints.gridy = 3;
        this.add(singlePlayerButton, constraints);
        
        // configure and add multiplayer button to panel
        this.multiplayerGameButton = new JButton(multiplayerButtonText);
        constraints.gridx = 2;
        constraints.gridy = 4;
        this.add(multiplayerGameButton, constraints);
    }
}
