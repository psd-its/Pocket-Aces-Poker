package model.player;


import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class TurnTimer extends Timer implements Serializable 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Runnable task;
    private TimerTask timerTask;

    public void schedule(Runnable runnable, long delay)
    {
        task = runnable;
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                task.run();
            }
        };
        this.schedule(timerTask, delay);
    }

    public void reschedule(long delay)
    {
        timerTask.cancel();
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                task.run();
            }
        };
        this.schedule(timerTask, delay);
    }
    
    public void cancelTask()
    {
        if (timerTask != null)
            timerTask.cancel();
    }
}
