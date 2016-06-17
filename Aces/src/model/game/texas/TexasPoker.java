package model.game.texas;

import model.game.Game;
import model.game.Hand;
import model.player.Player;
import model.table.Table;

public class TexasPoker implements Game
{
    private Table table;
    public TexasPoker()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addTable(Table table)
    {
        // Table object stored as an instance var
        this.table = table;
    }

    @Override
    public void addPlayer(Player player)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dealCards()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Hand checkForWinner(Player player)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void takeTurn(Player player)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Player[] getPlayers()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
