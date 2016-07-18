package view.screen.cell;

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
        this.name = new JLabel(player.getName());
        this.balance = new JLabel(Integer.toString(player.getBalance()));
        
        this.add(name);
        this.add(balance);
    }

    @Override
    public void refresh()
    {
        this.balance.setText(Integer.toString(this.player.getBalance()));
    }
}
