package model.card;

public class Straight extends Exception
{
    private Face high;
    // exception to signal straight has been found.
    public Straight(Face high)
    {
        //High signifies the highest card of the straight
        this.high = high;
    }
    
    public Face getHighCard()
    {
        return high;
    }
    
    public Straight(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public Straight(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public Straight(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public Straight(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
