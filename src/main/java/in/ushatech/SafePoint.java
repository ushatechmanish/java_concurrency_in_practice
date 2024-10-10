package in.ushatech;

import in.ushatech.annotation.GuardedBy;
import in.ushatech.annotation.ThreadSafe;

@ThreadSafe
public class SafePoint
{
    @GuardedBy("this")
    private int x, y;

    private SafePoint(int[] a)
    {
        this(a[0], a[1]);
    }

    // This is to create a deep copy of the class
    // used instead of clone as clone method has many issues with shallow vs deep
    // managing inheritance , clone nit supported exception , explicit control
    // over the creation of new object
    public SafePoint(SafePoint a)
    {
        this(a.get());
    }

    public SafePoint(final int x, final int y)
    {
        this.x = x;
        this.y = y;
    }


    // returns both state in one go
    public synchronized int[] get()
    {
        return new int[]{x, y};
    }

    // sets both values at one time
    public synchronized void set(final int x, final int y)
    {
        this.x = x;
        this.y = y;
    }
}
