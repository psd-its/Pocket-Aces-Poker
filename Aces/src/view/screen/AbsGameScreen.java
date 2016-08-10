/**
 * Abstract screen class, extends jpanel and has the common functionality
 * that all screens of the aplication will share.
 * 
 * @author Mathew Harrington
 */

package view.screen;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import model.facade.AcesFacade;
import view.main.MainView;

public abstract class AbsGameScreen extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 8514159757897058230L;
    protected MainView mainView;
    protected AcesFacade facade;
    protected GridBagConstraints constraints;
      
    public AbsGameScreen(MainView mainView, AcesFacade facade)
    {
        super(new GridBagLayout());
        this.mainView = mainView;
        this.facade = facade;
        this.constraints = new GridBagConstraints();
        this.constraints.insets = new Insets(15,15,15,15);
    }
    
    /**
     * Performs basic setup of screen. Should be called on screen switch.
     */
    public abstract void load();
    
    /**
     * @return GridBageConstraints A reference to the constraints object.
     */
    public GridBagConstraints getConstraints()
    {
        return this.constraints;
    }
    
    /**
     * @return MainView A reference to the main view of the application.
     */
    public MainView getMainView()
    {
        return this.mainView;
    }
    
    /**
     * @return AcesFacade A reference to the model facade object.
     */
    public AcesFacade getFacade()
    {
        return this.mainView.getFacade(); // Getting this from the mainView for multiplayer
    }
}
