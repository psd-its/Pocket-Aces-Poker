package model.table;

import model.card.Deck;
import model.player.Player;

// Need to implement concrete classes before use
public abstract class AbsTable implements Table
{
    protected Deck deck;
    protected int dealer;
    protected int pot;
    protected int blind;
    protected Player seats[];
    public AbsTable()
    {
        this.dealer = 0;
        this.pot = 0;
        this.blind = Table.START_BLIND;
        this.seats = new Player[Table.MAX_PLAYERS];
        this.deck = new Deck();
    } 

}