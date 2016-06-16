package model.table;

import model.card.Deck;
import model.player.Player;

public abstract class PokerTable implements Table
{
    protected Deck deck;
    protected int dealer;
    protected int pot;
    protected int blind;
    protected Player seats[];
    public PokerTable()
    {
        this.dealer = 0;
        this.pot = 0;
        this.blind = Table.START_BLIND;
        this.seats = new Player[Table.MAX_PLAYERS];
        this.deck = new Deck();
    } 

}
