package model.table;

import model.Rand;
import model.card.Card;
import model.card.Deck;
import model.player.Player;
/**
 * Concrete table class for Texas hold'em poker
 * @author Tristan s3528615
 *
 */
public class TexasTable extends AbsTable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int playersAtTable;
    private Card cardsInPlay[];
    private int playersDealt;
    public TexasTable()
    {
        // Call super class constructor
        super();
        this.playersAtTable = 0; //This is not really necessary just use a null check when iterating
        this.setCurrentBet(0);
        this.cardsInPlay = new Card[5];
        this.playersDealt = 0;
    }

    @Override
    public void dealCards()
    {
        // local vars
        int card, cardsDealt = 0;
        playersDealt = 0;
        int position = 1 + dealer;
        boolean cardFound = false;
        // deal a Texas hold'em hand
        deck.reset();
        do
        {
            // Avoid array out of bounds exception
            if (position == Table.MAX_PLAYERS)
            {
                //System.out.println("array reset");
                position = 0;
            }
            //check the seat contains a valid player 
            if (seats[position] != null)
            {
                if(seats[position].getBalance() > 0)
                {
                    seats[position].setPlayingHand(true);
                }
                while(!cardFound)
                {
                    // Random position of a card
                    card = Rand.getRand().nextInt(52);
                    // ensure the card isn't in play already
                    if (!deck.getCards()[card].isInPlay())
                    {
                        // Add the card to the players hand
                        if (position == dealer)
                        {
                            seats[position].getHand()[cardsDealt++] = 
                                    deck.getCards()[card];                           
                        }
                        else
                        {
                            seats[position].getHand()[cardsDealt] = 
                                    deck.getCards()[card];                           
                        }
                        deck.getCards()[card].setInPlay(true);
                        ++playersDealt;
                        cardFound = true;
                    }
                }
            }
            cardFound = false;
            ++position;
        }while(!(cardsDealt > 1));
        
        // deal the flop
        
        cardsDealt = 0;
        while (cardsDealt < Table.MAX_CARDS)
        {
            cardFound = false;
            // Check if we need to burn a card
            if (cardsDealt == Table.BURN_ONE ||
                cardsDealt == Table.BURN_TWO ||
                cardsDealt == Table.BURN_THREE)
            {
                // burn a card
                while (!cardFound)
                {
                    card = Rand.getRand().nextInt(52);
                    // ensure the card isn't in play already
                    if (!deck.getCards()[card].isInPlay())
                    {
                        deck.getCards()[card].setInPlay(true);
                        cardFound = true;
                    }
                }
            }
            cardFound = false;
            while (!cardFound)
            {
                card = Rand.getRand().nextInt(52);
                // ensure the card isn't in play already
                if (!deck.getCards()[card].isInPlay())
                {
                    cardsInPlay[cardsDealt++] =  deck.getCards()[card];
                    deck.getCards()[card].setInPlay(true);
                    cardFound = true;
                }
            }
        }
        

    }

    @Override
    public boolean placeBet(Player player, int ammount)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Player[] getSeats()
    {
        // Get players at the table
        return seats;
    }

    @Override
    public int getPot()
    {
        // TODO Auto-generated method stub
        return pot;
    }

    @Override
    public void addToPot(int bet)
    {
        // TODO Auto-generated method stub
        pot += bet;

    }

    @Override
    public int getBlind()
    {
        // TODO Auto-generated method stub
        return blind;
    }

    @Override
    public void setBlind(int blind)
    {
        // TODO Auto-generated method stub
        this.blind = blind;

    }

    @Override
    public Deck getDeck()
    {
        // TODO Auto-generated method stub
        return deck;
    }

    @Override
    public int getDealer()
    {
        // TODO Auto-generated method stub
        return dealer;
    }

    @Override
    public void setDealer(int dealer)
    {
        // function increments to the next valid dealer
        // if the dealer passed in is invalid
        boolean found = false;
        while(!found)
        {
            // Avoid out of bounds exception
            if (dealer >= seats.length)
            {
                dealer = 0;
            }
            // check that dealer is pointing to a valid player
            if(seats[dealer] != null)
            {
                found = true; // redundant but meh
                break; // stops dealer being incremented again
            }
            ++dealer;
        }
        this.dealer = dealer;
    }
    
    public Card[] getCardsInPlay()
    {
        return cardsInPlay;
    }

    @Override
    public void addPlayer(Player player)
    {
        // Add a player to the table and increment the player count
        seats[playersAtTable++] = player;
    }

    /**
     * @return the currentBet
     */
    public int getCurrentBet()
    {
        return currentBet;
    }

    /**
     * @param currentBet the currentBet to set
     */
    public void setCurrentBet(int currentBet)
    {
        this.currentBet = currentBet;
    }

    @Override
    public void setCardsInPlay(Card[] cards)
    {
        // method is used for test purposes only.
        this.cardsInPlay = cards;
        
    }

    /**
     * Count players still playing at the table
     * @return active player count
     */
    public int playerCount()
    {
        int count = 0;
        for(Player p : seats)
        {
            if (p != null)
            {
                if (p.getBalance() > 0)
                {
                    ++count;
                }
            }
        }
        return count;
    }

    @Override
    public void resetPot()
    {
        // reset the pot for a new round
        this.pot = 0;
    }

    @Override
    public void resetBets()
    {
        // Reset the bet for the table
        this.setCurrentBet(0);
        // Reset all the players current bet
        for (Player p : seats)
        {
            if (p != null)
            {
                p.setCurrentBet(0);
            }
        }        
    }
   
    

}
