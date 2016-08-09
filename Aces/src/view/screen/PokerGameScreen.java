/**
 * TexasGameScreen, the game playing screen for a texas hold'em game. I've got
 * an idea that seems to be working, dividing the screen into a 7x6 grid that
 * will fit the layout designed by Manuel. This is a quick and dirty implementation
 * for now just to see that all the panels etc are playing together nicely.
 * 
 * @author Mathew Harrington
 */

package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import controller.PokerGameScreenController;
import model.facade.AcesFacade;
import model.game.texas.TexasPoker;
import model.player.Player;
import view.main.MainView;
import view.screen.cell.AbsCell;
import view.screen.cell.CardDisplayCell;
import view.screen.cell.PlayerCell;
import view.screen.cell.PotDisplayCell;
import view.screen.cell.UserHandCell;
import view.screen.cell.UserInfoCell;

public class PokerGameScreen extends AbsGameScreen implements Observer
{
    private GridBagConstraints c;
    private PokerGameScreenController controller;
    Player[] players;
    private Map<String, AbsCell> cells;
    private JTextArea raiseInput;

    /**
     * TexasGameScreen constructor, sets visibility to false, the default for
     * all screens.
     * 
     * @param MainView
     *            A reference to the main view.
     * @param Facade
     *            A reference to the aces facade.
     */
    public PokerGameScreen(MainView mainView, AcesFacade facade)
    {
        super(mainView, facade);
        this.setVisible(false);
        this.cells = new HashMap<String, AbsCell>();
        this.controller = new PokerGameScreenController(facade, this, mainView);
    }

    /**
     * This method loads the background image for this screen.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Image bgImage = null;
        try
        {
            bgImage = ImageIO.read(new File("src/assets/BG_GAMESCREEN_L.png"));
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.paintComponent(g);
            Dimension d = getSize();
            g.drawImage(bgImage, 0, 0, d.width, d.height, null);
  }

    /**
     * Loads grid, buttons etc.
     */
    @Override
    public void load()
    {

        players = super.getFacade().getGame().getPlayers();

        // Think this is cleaner mat change if you see fit
        controller.setUser(super.getFacade().getPlayer(0));
        allocateGrid();
    
    }

    /**
     * Divides game screen into workable grid, matching as closely as I could to
     * the designs given.
     */
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
        if (!addPlayerCell(c, 4))
        {
            this.add(createEmptyPane(), c);
        }

        // 1x4 P5 cell
        c.gridx = 3;
        if (!addPlayerCell(c, 5))
        {
            this.add(createEmptyPane(), c);
        }

        // 1x5 - P6 cell
        c.gridx = 4;
        if (!addPlayerCell(c, 6))
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
        if (!addPlayerCell(c, 3))
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
        if (!addPlayerCell(c, 7))
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
        c.gridx = 1;
        c.gridheight = 2;
        addCardDisplayCell(c, 1);

        // 3x3 - Display card 2
        c.gridx = 2;
        addCardDisplayCell(c, 2);

        // 3x4 - Display card 3
        c.gridx = 3;
        addCardDisplayCell(c, 3);

        // 3x5 - Display card 4
        c.gridx = 4;
        addCardDisplayCell(c, 4);

        // 3x6 - Display card 5
        c.gridx = 5;
        addCardDisplayCell(c, 5);

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
        if (!addPlayerCell(c, 2))
        {
            this.add(createEmptyPane(), c);
        }

        // 4x7 - P8 cell
        c.gridx = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        if (!addPlayerCell(c, 8))
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
        if (!addPlayerCell(c, 1))
        {
            this.add(createEmptyPane(), c);
        }

        // 5x4 - P0 (USER) cell
        c.gridx = 3;
        this.addUserHandCell(c);

        // 5x5 - P9 cell
        c.gridx = 4;
        if (!addPlayerCell(c, 9))
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
        this.add(pane, c);

        // 6x3 - Call button
        pane = createButtonPane("call");
        c.gridx = 2;
        this.add(pane, c);

        // 6x4 - Name + Balance cell
        c.gridx = 3;
        this.addUserInfoCell(c);

        // 6x5 - Raise button
        pane = createButtonPane("raise $");
        c.gridx = 4;
        this.add(pane, c);

        // 6x6 - Raise input field
        this.raiseInput = new JTextArea(1, 10);
        this.raiseInput.setDoubleBuffered(true);
        pane = createEmptyPane();
        pane.add(this.raiseInput);
        // c.gridx = 5;
        c.gridx = GridBagConstraints.RELATIVE;
        this.add(pane, c);

        // 6x7
        pane = createEmptyPane();
        c.gridx = 5;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(pane, c);
    }

    /**
     * Updates all cells on the screen.
     */
    public void updateAllCells()
    {
        for (AbsCell cell : cells.values())
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
     * Updates the specific cell selected, playerNum corresponds to player seat
     * in array.
     * 
     * @param playerNum
     *            The index at which the player reference is stored.
     */
    public void updatePlayerCell(int playerNum)
    {
        String player = "player" + playerNum;
        AbsCell playerCell = cells.get(player);
        playerCell.refresh();
        
    }

    /**
     * Updates the user's hand cell.
     */
    public void updateUserHand()
    {
        AbsCell userCell = cells.get("userHandCell");
        userCell.refresh();
       
    }

    /**
     * Updates the user's balance.
     */
    public void updateUserBalance()
    {
        AbsCell cell = cells.get("userInfoCell");
        cell.refresh();
                
    }

    /**
     * Creates new player cell, adds it to the screen and the map of cells.
     * 
     * @param c
     *            The grid bag constraints.
     * @param playerNum
     *            The index in the players array.
     * @return true if new player cell added to screen.
     */
    private boolean addPlayerCell(GridBagConstraints c, int playerNum)
    {
        if (players[playerNum] != null)
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
     * Adds a user cell to the game screen, the cell where the user's hand will
     * be displayed. The cell is added to the cells map.
     * 
     * @param c
     *            The grid bag constraints.
     */
    private void addUserHandCell(GridBagConstraints c)
    {
        UserHandCell uCell = new UserHandCell(facade, players[0]);
        this.add(uCell, c);
        cells.put("userHandCell", uCell);
    }

    /**
     * Adds a user info cell to the game screen, this cell will contain the
     * user's name and current balance.
     * 
     * @param c
     *            The grid bag constraints.
     */
    private void addUserInfoCell(GridBagConstraints c)
    {
        UserInfoCell uiCell = new UserInfoCell(facade, players[0]);
        this.add(uiCell, c);
        cells.put("userInfoCell", uiCell);
    }

    /**
     * Adds a card display cell to the center area of the game screen.
     * 
     * @param c
     *            The grid bag constraints.
     */
    private void addCardDisplayCell(GridBagConstraints c, int i)
    {
        CardDisplayCell cdCell = new CardDisplayCell(facade, facade.getGame()
                .getTable(), i);
        this.add(cdCell, c);
        cells.put("cardDisplayCell" + i, cdCell);
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
        JPanel panel = new JPanel(new BorderLayout());
        // panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }

    /**
     * Creates a pane with a button in it, should we add the action listener
     * here? Make all buttons in center of their pane.
     * 
     * @param buttonName
     *            The text to be displayed on the button.
     */
    private JPanel createButtonPane(String buttonName)
    {
        JPanel buttonPane = createEmptyPane();
        JButton button = new JButton(buttonName);
        button.addActionListener(controller);
        buttonPane.setOpaque(false);

        buttonPane.add(button);
        return buttonPane;
    }

    public JTextArea getRaiseInput()
    {
        return this.raiseInput;
    }

    /**
     * The second argument will be the TexasPoker object.
     */
    @Override
    public void update(Observable o, Object arg)
    {
        // build fresh screen
        PokerGameScreen nextIteration = new PokerGameScreen(this.mainView, this.facade);
        TexasPoker game = (TexasPoker)this.facade.getGame();
        game.addObserver(nextIteration);
        game.deleteObserver(this);
        
        // load fresh screens cells etc
        nextIteration.load();
        nextIteration.updateAllCells();
        nextIteration.updateUserBalance();
        nextIteration.updateUserHand();

        // add fresh screen to main view
        nextIteration.mainView.getContentPane().add(nextIteration, BorderLayout.CENTER); 
        
        // make fresh screen visible
        nextIteration.setVisible(true);
        this.setVisible(false);
        this.removeAll();
//        
//        this.load();
//        this.updateAllCells();
//        this.updateUserBalance();
//        this.updateUserHand();
//        
//        this.revalidate();
//        this.repaint();
    }
}
