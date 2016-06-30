package model.card;

/**
 * Mock Tuple representation
 * 
 * @author Tristan s3528615
 */
public class Tup<F, L>
{
    // Made these final so they are now immutable 
    public final F f;
    public final L l;
    // Tuple pair
    public Tup(F first, L last)
    {
        this.f = first;
        this.l = last;
    }
}
