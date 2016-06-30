package test_harness;

import model.card.Card;
import model.card.Face;
import model.card.Suit;
import model.card.WinningHands;
import model.game.Game;
import model.player.Player;

public class RecTest
{
    private Game g;
    private Card[] cards;
    public RecTest(Game texas)
    {
        // Table we are testing with
        this.g = texas;
        cards = new Card[5];
    }
    
    public void testAll(int pos) throws Exception
    {
        // Exit condition for recursive function
        if (pos > 9) return;
        Player[] p;
        WinningHands winTest = WinningHands.values()[pos];
        System.out.println("Testing " + winTest);
        switch(winTest)
        {
            // Set test conditions for a flush with split pot
            case FLUSH:
                cards[0] = new Card(Suit.CLUBS, Face.JACK);
                cards[1] = new Card(Suit.CLUBS, Face.SIX);
                cards[2] = new Card(Suit.CLUBS, Face.ACE);
                cards[3] = new Card(Suit.DIAMONDS, Face.JACK);
                cards[4] = new Card(Suit.HEARTS, Face.TWO);
                p = g.getTable().getSeats();
                Card[] p1 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.KING)};
                Card[] p2 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p3 = {new Card(Suit.DIAMONDS, Face.ACE), 
                        new Card(Suit.CLUBS, Face.TWO)};
                Card[] p4 = {new Card(Suit.CLUBS, Face.SEVEN), 
                        new Card(Suit.CLUBS, Face.QUEEN)};
                p[0].setHand(p1);
                p[1].setHand(p2);
                p[2].setHand(p3);
                p[3].setHand(p4);
                g.getTable().setCardsInPlay(cards);
                break;
            case FOUR_OF_A_KIND:
                cards[0] = new Card(Suit.CLUBS, Face.JACK);
                cards[1] = new Card(Suit.HEARTS, Face.SIX);
                cards[2] = new Card(Suit.CLUBS, Face.ACE);
                cards[3] = new Card(Suit.DIAMONDS, Face.JACK);
                cards[4] = new Card(Suit.HEARTS, Face.TWO);
                p = g.getTable().getSeats();
                Card[] p5 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.KING)};
                Card[] p6 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p7 = {new Card(Suit.DIAMONDS, Face.ACE), 
                        new Card(Suit.CLUBS, Face.TWO)};
                Card[] p8 = {new Card(Suit.SPADES, Face.JACK), 
                        new Card(Suit.HEARTS, Face.JACK)};
                p[0].setHand(p5);
                p[1].setHand(p6);
                p[2].setHand(p7);
                p[3].setHand(p8);
                g.getTable().setCardsInPlay(cards);
                break;
            case FULL_HOUSE:
                cards[0] = new Card(Suit.CLUBS, Face.JACK);
                cards[1] = new Card(Suit.HEARTS, Face.SIX);
                cards[2] = new Card(Suit.CLUBS, Face.ACE);
                cards[3] = new Card(Suit.DIAMONDS, Face.JACK);
                cards[4] = new Card(Suit.HEARTS, Face.TWO);
                p = g.getTable().getSeats();
                Card[] p9 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.KING)};
                Card[] p10 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p11 = {new Card(Suit.DIAMONDS, Face.ACE), 
                        new Card(Suit.CLUBS, Face.TWO)};
                Card[] p12 = {new Card(Suit.SPADES, Face.JACK), 
                        new Card(Suit.HEARTS, Face.ACE)};
                p[0].setHand(p9);
                p[1].setHand(p10);
                p[2].setHand(p11);
                p[3].setHand(p12);
                g.getTable().setCardsInPlay(cards);
                break;
            case HIGH_CARD:
                cards[0] = new Card(Suit.CLUBS, Face.FIVE);
                cards[1] = new Card(Suit.HEARTS, Face.SIX);
                cards[2] = new Card(Suit.CLUBS, Face.ACE);
                cards[3] = new Card(Suit.DIAMONDS, Face.JACK);
                cards[4] = new Card(Suit.HEARTS, Face.TWO);
                p = g.getTable().getSeats();
                Card[] p13 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.KING)};
                Card[] p14 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p15 = {new Card(Suit.DIAMONDS, Face.FOUR), 
                        new Card(Suit.CLUBS, Face.TEN)};
                Card[] p16 = {new Card(Suit.SPADES, Face.SEVEN), 
                        new Card(Suit.HEARTS, Face.NINE)};
                p[0].setHand(p13);
                p[1].setHand(p14);
                p[2].setHand(p15);
                p[3].setHand(p16);
                g.getTable().setCardsInPlay(cards);
                break;
            case PAIR:
                cards[0] = new Card(Suit.CLUBS, Face.FIVE);
                cards[1] = new Card(Suit.HEARTS, Face.KING);
                cards[2] = new Card(Suit.CLUBS, Face.ACE);
                cards[3] = new Card(Suit.DIAMONDS, Face.JACK);
                cards[4] = new Card(Suit.HEARTS, Face.TWO);
                p = g.getTable().getSeats();
                Card[] p17 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.KING)};
                Card[] p18 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p19 = {new Card(Suit.DIAMONDS, Face.TWO), 
                        new Card(Suit.CLUBS, Face.TEN)};
                Card[] p20 = {new Card(Suit.SPADES, Face.SEVEN), 
                        new Card(Suit.HEARTS, Face.NINE)};
                p[0].setHand(p17);
                p[1].setHand(p18);
                p[2].setHand(p19);
                p[3].setHand(p20);
                g.getTable().setCardsInPlay(cards);
                break;
            case ROYAL_FLUSH:
                cards[0] = new Card(Suit.CLUBS, Face.QUEEN);
                cards[1] = new Card(Suit.CLUBS, Face.KING);
                cards[2] = new Card(Suit.CLUBS, Face.ACE);
                cards[3] = new Card(Suit.CLUBS, Face.JACK);
                cards[4] = new Card(Suit.CLUBS, Face.TEN);
                p = g.getTable().getSeats();
                Card[] p21 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p22 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.HEARTS, Face.THREE)};
                Card[] p23 = {new Card(Suit.DIAMONDS, Face.TWO), 
                        new Card(Suit.CLUBS, Face.EIGHT)};
                Card[] p24 = {new Card(Suit.SPADES, Face.SEVEN), 
                        new Card(Suit.HEARTS, Face.NINE)};
                p[0].setHand(p21);
                p[1].setHand(p22);
                p[2].setHand(p23);
                p[3].setHand(p24);
                g.getTable().setCardsInPlay(cards);
                break;
            case STRAIGHT:
                cards[0] = new Card(Suit.CLUBS, Face.JACK);
                cards[1] = new Card(Suit.CLUBS, Face.KING);
                cards[2] = new Card(Suit.HEARTS, Face.SEVEN);
                cards[3] = new Card(Suit.CLUBS, Face.NINE);
                cards[4] = new Card(Suit.DIAMONDS, Face.TEN);
                p = g.getTable().getSeats();
                Card[] p25 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p26 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.HEARTS, Face.THREE)};
                Card[] p27 = {new Card(Suit.DIAMONDS, Face.TWO), 
                        new Card(Suit.CLUBS, Face.EIGHT)};
                Card[] p28 = {new Card(Suit.SPADES, Face.SEVEN), 
                        new Card(Suit.HEARTS, Face.NINE)};
                p[0].setHand(p25);
                p[1].setHand(p26);
                p[2].setHand(p27);
                p[3].setHand(p28);
                g.getTable().setCardsInPlay(cards);
                break;
            case STRAIGHT_FLUSH:
                cards[0] = new Card(Suit.HEARTS, Face.QUEEN);
                cards[1] = new Card(Suit.CLUBS, Face.SIX);
                cards[2] = new Card(Suit.CLUBS, Face.SEVEN);
                cards[3] = new Card(Suit.CLUBS, Face.NINE);
                cards[4] = new Card(Suit.CLUBS, Face.TEN);
                p = g.getTable().getSeats();
                Card[] p29 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p30 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.HEARTS, Face.THREE)};
                Card[] p31 = {new Card(Suit.DIAMONDS, Face.TWO), 
                        new Card(Suit.CLUBS, Face.EIGHT)};
                Card[] p32 = {new Card(Suit.SPADES, Face.SEVEN), 
                        new Card(Suit.HEARTS, Face.NINE)};
                p[0].setHand(p29);
                p[1].setHand(p30);
                p[2].setHand(p31);
                p[3].setHand(p32);
                g.getTable().setCardsInPlay(cards);
                break;
            case THREE_OF_A_KIND:
                cards[0] = new Card(Suit.CLUBS, Face.JACK);
                cards[1] = new Card(Suit.HEARTS, Face.SIX);
                cards[2] = new Card(Suit.CLUBS, Face.FOUR);
                cards[3] = new Card(Suit.DIAMONDS, Face.JACK);
                cards[4] = new Card(Suit.HEARTS, Face.TWO);
                p = g.getTable().getSeats();
                Card[] p33 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.CLUBS, Face.KING)};
                Card[] p34 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p35 = {new Card(Suit.DIAMONDS, Face.ACE), 
                        new Card(Suit.CLUBS, Face.TWO)};
                Card[] p36 = {new Card(Suit.SPADES, Face.ACE), 
                        new Card(Suit.HEARTS, Face.JACK)};
                p[0].setHand(p33);
                p[1].setHand(p34);
                p[2].setHand(p35);
                p[3].setHand(p36);
                g.getTable().setCardsInPlay(cards);
                break;
            case TWO_PAIR:
                cards[0] = new Card(Suit.CLUBS, Face.KING);
                cards[1] = new Card(Suit.HEARTS, Face.SIX);
                cards[2] = new Card(Suit.CLUBS, Face.ACE);
                cards[3] = new Card(Suit.DIAMONDS, Face.JACK);
                cards[4] = new Card(Suit.HEARTS, Face.TWO);
                p = g.getTable().getSeats();
                Card[] p37 = {new Card(Suit.DIAMONDS, Face.TEN), 
                        new Card(Suit.SPADES, Face.KING)};
                Card[] p38 = {new Card(Suit.CLUBS, Face.EIGHT), 
                        new Card(Suit.CLUBS, Face.THREE)};
                Card[] p39 = {new Card(Suit.DIAMONDS, Face.ACE), 
                        new Card(Suit.CLUBS, Face.TWO)};
                Card[] p40 = {new Card(Suit.SPADES, Face.ACE), 
                        new Card(Suit.HEARTS, Face.JACK)};
                p[0].setHand(p37);
                p[1].setHand(p38);
                p[2].setHand(p39);
                p[3].setHand(p40);
                g.getTable().setCardsInPlay(cards);
                break;
            default:
                break;
            
        }
        g.checkForWinner();
        System.out.println("Test completed.");
        testAll(++pos);
    }

}
