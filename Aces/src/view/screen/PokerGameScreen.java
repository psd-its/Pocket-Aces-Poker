/**
 * TexasGameScreen, the game playing screen for a texas hold'em game. I've got
 * an idea that seems to be working, dividing the screen into a 7x6 grid that
 * will fit the layout designed by Manuel. This is a quick and dirty implementation
 * for now just to see that all the panels etc are playing together nicely.
 * 
 * We'll need to add specific names to the cells (except the empty ones) like
 * displayCard1Cell or player6Cell, so we can update them from the controller, 
 * all the ones here so far are nameless.
 * 
 * TODO
 * Instance variables for all panes we'll need to update.
 * Finish createUserPane()
 * Finish createPlayerPane()
 * Add action listener (controller) to buttons.
 * Add real player names, from players array (check for null at index first).
 * Remove borders when we're happy with where everything is sitting.
 * Add graphics.
 * 
 * @author Mathew Harrington
 */

package view.screen;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.facade.AcesFacade;
import model.player.Player;
import view.main.MainView;

public class PokerGameScreen extends AbsGameScreen
{
    private GridBagConstraints c;
    Player[] players;
    
    /**
     * TexasGameScreen constructor, sets visibility to false, the default for
     * all screens.
     * 
     * @param MainView A reference to the main view.
     * @param Facade A reference to the aces facade.
     */
    public PokerGameScreen(MainView mainView, AcesFacade facade)
    {
        super(mainView, facade);
        this.setVisible(false);  
    }
    
    private void allocateGrid()
    {
        // get a fresh constraints object
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        
        // begin allocating panes - these correspond to the diagram I've done
        
        // 1x1
        JPanel pane = createEmptyPane();
        c.gridx = 0;
        c.gridy = 0;
        this.add(pane, c);
        
        // 1x2
        pane = createEmptyPane();
        c.gridx = 1;
        this.add(pane, c);
        
        // 1x3 - P4 cell
        pane = createPlayerPane(4);
        c.gridx = 2;
        this.add(pane, c);
        
        // 1x4 - P5 cell
        pane = createPlayerPane(5);
        c.gridx = 3;
        this.add(pane, c);
        
        // 1x5 - P6 cell
        pane = createPlayerPane(6);
        c.gridx = 4;
        this.add(pane, c);
        
        // 1x6
        pane = createEmptyPane();
        c.gridx = 5;
        this.add(pane, c);
        
        // 1x7 - Exit button cell
        pane = createButtonPane("exit");
        c.gridx = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(pane, c);
        
        // reset grid width
        c.gridwidth = 1;
        
        // 2x1 - P3 cell
        pane = createPlayerPane(3);
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        this.add(pane, c);
        
        // 2x2
        pane = createEmptyPane();
        c.gridx = 1;
        this.add(pane, c);
        
        // 2x3
        pane = createEmptyPane();
        c.gridx = 2;
        this.add(pane, c);
        
        // 2x4 - POT cell
        pane = createTextBalancePane("POT", 500);
        c.gridx = 3;
        this.add(pane, c);
        
        // 2x5
        pane = createEmptyPane();
        c.gridx = 4;
        this.add(pane, c);
        
        // 2x6
        pane = createEmptyPane();
        c.gridx = 5;
        this.add(pane, c);
        
        // 2x7 - P7 cell
        pane = createPlayerPane(7);
        c.gridx = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(pane, c);
        
        // reset grid width
        c.gridwidth = 1;
        
        // 3x1
        pane = createEmptyPane();
        c.gridx = 0;
        c.gridy = 2;
        this.add(pane, c);
        
        // 3x2 - Display card 1
        pane = createEmptyPane();
        c.gridx = 1;
        c.gridheight = 2;
        this.add(pane, c);
        
        // 3x3 - Display card 2
        pane = createEmptyPane();
        c.gridx = 2;
        this.add(pane, c);
        
        // 3x4 - Display card 3
        pane = createEmptyPane();
        c.gridx = 3;
        this.add(pane, c);
        
        // 3x5 - Display card 4
        pane = createEmptyPane();
        c.gridx = 4;
        this.add(pane, c);
        
        // 3x6 - Display card 5
        pane = createEmptyPane();
        c.gridx = 5;
        this.add(pane, c);
        
        // 3x7
        pane = createEmptyPane();
        c.gridx = 6;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(pane, c);
        
        // reset grid width
        c.gridwidth = 1;
        
        // 4x1 - P2 cell
        pane = createPlayerPane(2);
        c.gridx = 0;
        c.gridy = 3;
        this.add(pane, c);
        
        // 4x7 - P8 cell
        pane = createPlayerPane(8);
        c.gridx = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(pane, c);
        
        // reset grid width
        c.gridwidth = 1;
        
        // 5x1
        pane = createEmptyPane();
        c.gridx = 0;
        c.gridy = 4;
        this.add(pane, c);
        
        // 5x2
        pane = createEmptyPane();
        c.gridx = 1;
        this.add(pane, c);
        
        // 5x3 - P1 Cell
        pane = createPlayerPane(1);
        c.gridx = 2;
        this.add(pane, c);
        
        // 5x4 - P0 (USER) cell
        pane = createUserPane();
        c.gridx = 3;
        this.add(pane);
        
        // 5x5 - P9 cell
        pane = createPlayerPane(9);
        c.gridx = 4;
        this.add(pane, c);
        
        // 5x6
        pane = createEmptyPane();
        c.gridx = 5;
        this.add(pane, c);
        
        // 5x7
        pane = createEmptyPane();
        c.gridx = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(pane, c);
        
        // reset grid width
        c.gridwidth = 1;
        
        // 6x1
        pane = createEmptyPane();
        c.gridx = 0;
        c.gridy = 5;
        this.add(pane, c);
        
        // 6x2 - Fold button
        pane = createButtonPane("fold");
        c.gridx = 1;
        this.add(pane);
        
        // 6x3 - Call button
        pane = createButtonPane("call");
        c.gridx = 2;
        this.add(pane);
        
        // 6x4 - Name + Balance cell
        pane = createTextBalancePane(players[0].getName(), players[0].getBalance());
        c.gridx = 3;
        this.add(pane);
        
        // 6x5 - Raise button
        pane = createButtonPane("raise $");
        c.gridx = 4;
        this.add(pane);
        
        // 6x6 - Raise input field
        pane = createTextInputPane(1, 10);
        c.gridx = 5;
        this.add(pane);
        
        // 6x7
        pane = createEmptyPane();
        c.gridx = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(pane, c);
    }
    
    /**
     * Returns an empty pane, I have border turned on while we get this all
     * working, to keep track of where things are and how much space they're
     * taking up.
     * 
     * @return JPanel An empty panel.
     */
    private JPanel createEmptyPane()
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }
    
    
    /**
     * Will contain nested panes, player's name, balance and status will need
     * to be represented along with the backs of their cards. A null check is needed
     * as some seats in the array may be empty.
     * 
     * @param int playerNum The table loaction of player cell, will correspond with the players array.
     * @return JPanel A player panel.
     */
    private JPanel createPlayerPane(int playerNum)
    {   
        if(players[playerNum] != null)
        {
            JPanel playerPanel = createEmptyPane();
            JLabel name = new JLabel(players[playerNum].getName());
            JLabel balance = new JLabel(Integer.toString(players[playerNum].getBalance()));
            
            playerPanel.add(name);
            playerPanel.add(balance);
            
            return playerPanel;
        }
        
        else
        {
            // this could be createEmptyPlayerPane? Depends how we want to display empty seats
            return createEmptyPane();
        }
    }
    
    /**
     * Will just contain user's hand, face up, as they wont have any cards
     * to begin with just allocate space for them with nested panes.
     * 
     * @return JPanel The pane divided into two for the user's cards to be
     * displayed in.
     */
    private JPanel createUserPane()
    {
        return createPlayerPane(0);
    }
    
    /**
     * Displays supplied text and given amount, can be used for the 'pot' cell
     * or inside a player cell for their name+balance.
     * 
     * @param text The text associated with the amount.
     * @param balance The amount to be displayed.
     * @return JPanel Panel divided into required sections (see diagram).
     */
    private JPanel createTextBalancePane(String text, int balance)
    {
        JLabel title, amount;
        JPanel textBalancePane = createEmptyPane();
        
        title = new JLabel(text);
        amount = new JLabel(Integer.toString(balance));
        
        textBalancePane.add(title);
        textBalancePane.add(amount);
        
        return textBalancePane;
    }
    
    /**
     * Creates a pane with a button in it, should we add the action listener
     * here? Make all buttons in center of their pane.
     * 
     * @param buttonName The text to be displayed on the button.
     */
    private JPanel createButtonPane(String buttonName)
    {
        JPanel buttonPane = createEmptyPane();
        JButton button = new JButton(buttonName);
        
        // TODO add action listener here
        
        buttonPane.add(button);
        
        return buttonPane;
    }
    
    /**
     * Creates a JPanel containing a text input field of the specified size,
     * rows/cols.
     * 
     * @return JPanel A panel containing a text input field.
     */
    private JPanel createTextInputPane(int rows, int cols)
    {
        JPanel inputPanel = createEmptyPane();
        JTextArea field = new JTextArea(rows, cols);
        
        inputPanel.add(field);
        
        return inputPanel;
    }

    @Override
    public void load()
    {
        players = super.getFacade().getGame().getPlayers();
        allocateGrid();
    }
}
