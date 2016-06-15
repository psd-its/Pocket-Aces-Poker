package model.player;

public abstract class AbsPlayer implements Player
{
    protected String name;
    protected boolean human;
    protected int cash;
    public AbsPlayer(String name, boolean Human)
    {
        this.name = name;
        this.human = human;
        this.cash = Player.START_BALANCE;
    }

}
