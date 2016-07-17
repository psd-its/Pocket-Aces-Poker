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
 * Remove borders when we're happy with where everything is sitting.
 * Add graphics.
 * 
 * @author Mathew Harrington
 */

package view.screen;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.facade.AcesFacade;
import model.player.Player;
import view.main.MainView;
import view.screen.cell.AbsCell;
import view.screen.cell.PlayerCell;
import view.screen.cell.PotDisplayCell;

public class PokerGameScreen extends AbsGameScreen
{
    private GridBagConstraints c;
    Player[] players;
    private Map<String, AbsCell> cells;
    
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
        this.cells = new HashMap<String, AbsCell>();
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
        c.gridx = 2;
        if(!addPlayerCell(c, 4))
        {
            this.add(createEmptyPane(), c);
        }
        
        // 1x4 P5 cell
        c.gridx = 3;
        if(!addPlayerCell(c, 5))
        {
            this.add(createEmptyPane(), c);
        }
        
        // 1x5 - P6 cell
        c.gridx = 4;
        if(!addPlayerCell(c, 6))
        {
            this.add(createEmptyPane(), c);
        }
        
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
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        if(!addPlayerCell(c, 3))
        {
            this.add(createEmptyPane(), c);
        }
        
        // 2x2
        pane = createEmptyPane();
        c.gridx = 1;
        this.add(pane, c);
        
        // 2x3
        pane = createEmptyPane();
        c.gridx = 2;
        this.add(pane, c);
        
        // 2x4 - POT cell
        AbsCell potCell = new PotDisplayCell(super.facade, 100000);
        c.gridx = 3;
        this.add(potCell);
        cells.put("potCell", potCell);
  
        // 2x5
        pane = createEmptyPane();
        c.gridx = 4;
        this.add(pane, c);
        
        // 2x6
        pane = createEmptyPane();
        c.gridx = 5;
        this.add(pane, c);
        
        // 2x7 - P7 cell
        c.gridx = 6;
        if(!addPlayerCell(c, 7))
        {
            this.add(createEmptyPane(), c);
        }
        
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
        c.gridx = 0;
        c.gridy = 3;
        if(!addPlayerCell(c, 2))
        {
            this.add(createEmptyPane(), c);
        }
        
        // 4x7 - P8 cell
        c.gridx = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        if(!addPlayerCell(c, 8))
        {
            this.add(createEmptyPane(), c);
        }
        
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
        c.gridx = 2;
        if(!addPlayerCell(c, 1))
        {
            this.add(createEmptyPane(), c);
        }
        
        // 5x4 - P0 (USER) cell
        c.gridx = 3;
        if(!addPlayerCell(c, 0))
        {
            this.add(createEmptyPane());
        }
        
        // 5x5 - P9 cell
        c.gridx = 4;
        if(!addPlayerCell(c, 9))
        {
            this.add(createEmptyPane(), c);
        }
        
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
        pane = createUserInfoPane(players[0]);
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
     * Updates all cells on the screen.
     */
    public void updateAllCells()
    {
        for(AbsCell cell : cells.values())
        {
            cell.refresh(); 
        }
    }
    
    /**
     * Updates the pot cell with the new amount in the pot.
     */
    public void updatePotCell()
    {
        AbsCell pot = cells.get("potCell");
        pot.refresh();
    }
    
    /**
     * Updates the specific cell selected, playerNum corresponds to player
     * seat in array.
     * 
     * @param playerNum The index at which the player reference is stored.
     */
    public void updatePlayerCell(int playerNum)
    {
        String player = "player" + playerNum;
        AbsCell playerCell = cells.get(player);
        playerCell.refresh();
    }
    
    /**
     * Updates the user info cell.
     */
    public void updateUserCell()
    {
        // we know user is player 0
        AbsCell userCell = cells.get("player0");
        userCell.refresh();
    }
    
    /**
     * Creates new player cell, adds it to the screen and the map of cells.
     * 
     * @param c The grid bag constraints
     * @param playerNum The index in the players array
     * @return true if new player cell added to screen.
     */
    private boolean addPlayerCell(GridBagConstraints c,int playerNum)
    {
        if(players[playerNum] != null)
        {
            PlayerCell pCell = new PlayerCell(facade, players[playerNum]);
            this.add(pCell, c);
            cells.put("player" + playerNum, pCell);
            return true;
        }
        
        else 
        {
            return false;
        }
    }
    
    /**
     * Displays supplied text and given amount, can be used for the 'pot' cell
     * or inside a player cell for their name+balance.
     * 
     * @param Player The player object representing the user.
     * @return JPanel Panel divided into required sections (see diagram).
     */
    private JPanel createUserInfoPane(Player player)
    {
        JLabel title, amount;
        JPanel userInfoPane = createEmptyPane();
        title = new JLabel(player.getName());
        amount = new JLabel(Integer.toString(player.getBalance()));
        userInfoPane.add(title);
        userInfoPane.add(amount);
        
        return userInfoPane;
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
     * @param int Number of rows the textarea should be.
     * @param int Number of columns the textarea should be.
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
