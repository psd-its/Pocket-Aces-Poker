/**
 * Swing Worker class for the game to run inside of. Had an issue with updating
 * the gui, swing components must remain on the dispatch thread so this class
 * was created to run the game outside the dispatch thread.
 * 
 * There's probably more that needs to be done to finish this class but so far
 * it has enabled game to commence without throwing exceptions and the gui to be 
 * updated after cards dealt.
 * 
 * @author Mathew Harrington
 */
package model.game.texas;

import java.util.List;

import javax.swing.SwingWorker;

import model.game.Game;

public class TexasGameWorker extends SwingWorker<Integer, String>
{
    private Game game;
    
    public TexasGameWorker(Game game)
    {
        this.game = game;
    }

    @Override
    protected Integer doInBackground() throws Exception
    {
        // added for debugging
        //publish("inside doInBackground()");
        
        game.play();
        return 0;
    }
    
    /**
     * Sent process() output to console, setup this way just for debugging.
     */
    @Override
    protected void process(final List<String> chunks)
    {
        for(String piece : chunks)
        {
            System.out.print(piece + " "); 
        }
        System.out.println("\n");
    }
    
    /**
     * Need to use this during game to check for interruptions to thread.
     * 
     * @throws InterruptedException
     */
    private static void failIfInterrupted() throws InterruptedException 
    {
        if (Thread.currentThread().isInterrupted()) 
        {
          throw new InterruptedException("Interrupted while playing game");
        }
    }
}
