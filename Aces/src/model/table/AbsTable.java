package model.table;

import model.card.Deck;
import model.game.texas.Const;
import model.player.Player;

// Need to implement concrete classes before use
public abstract class AbsTable implements Table
{
    /**
     * 
     */
    private static final long serialVersionUID = -8519131707146124795L;
    protected int currentBet;
    protected Deck deck;
    protected int dealer;
    protected int pot;
    protected int blind;
    protected Player seats[];
    public AbsTable()
    {
        this.dealer = 0;
        this.pot = 0;
        this.blind = Const.START_BLIND;
        this.seats = new Player[Table.MAX_PLAYERS];
        this.deck = new Deck();
    } 

}
