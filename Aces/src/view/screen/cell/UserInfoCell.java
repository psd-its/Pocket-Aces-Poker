package view.screen.cell;

import java.awt.Color;

import javax.swing.JLabel;
import model.facade.AcesFacade;
import model.player.Player;

public class UserInfoCell extends AbsCell
{
    private Player player;
    private JLabel name, balance;
    
    public UserInfoCell(AcesFacade facade, Player player)
    {
        super(facade);
        this.player = player;
        //this.name = new JLabel(player.getName());
        //this.balance = new JLabel(Integer.toString(player.getBalance()));
        
        String nameText = this.player.getName() + " ";
        String balanceText = "$" + Integer.toString(this.player.getBalance());
        
        this.name = new JLabel(nameText);
        this.balance = new JLabel(balanceText);
        
        this.name.setForeground(Color.LIGHT_GRAY);
        this.balance.setForeground(Color.LIGHT_GRAY);
        this.name.setFont(name.getFont().deriveFont(18.0f));
        this.balance.setFont(balance.getFont().deriveFont(18.0f));
        
        this.add(name);
        this.add(balance);
    }

    @Override
    public void refresh()
    {
        this.balance.setText("$" + Integer.toString(this.player.getBalance()));
    }
}
