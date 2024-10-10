package in.ushatech;

import in.ushatech.annotation.Immutable;

@Immutable
public class Point
{
    //    private final int x, y;
    // used public so that get can work effortlessly and set is not allowed due to final keyword
    public final int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
