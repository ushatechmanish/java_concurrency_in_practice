package in.ushatech;

import in.ushatech.annotation.NotThreadSafe;

@NotThreadSafe
public class MutablePoint
{
    // exposed
    public int x, y;

    public MutablePoint()
    {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p)
    {
        this.x = p.x;
        this.y = p.y;
    }
}
