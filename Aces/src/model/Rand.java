package model;

import java.util.Random;

/**
 * Random generator as singleton
 * @author Tristan s3528615
 *
 */
public class Rand
{
    private static Random rand = null;
    private Rand()
    {
        // seed the random generator with current time
        rand = new Random(System.currentTimeMillis() % 1000);
    }
    
    public static Random getRand()
    {
        if (rand == null)
        {
            new Rand();
        }
        return rand;
    }


}
