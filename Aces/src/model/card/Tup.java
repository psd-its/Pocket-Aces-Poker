package model.card;

/**
 * Mock Tuple representation
 * 
 * @author Tristan s3528615
 */
public class Tup<F, L>
{
    public F f;
    public L l;

    public Tup(F first, L last)
    {
        this.f = first;
        this.l = last;
    }
}
