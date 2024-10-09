package in.ushatech;

import in.ushatech.annotation.GuardedBy;
import in.ushatech.annotation.ThreadSafe;

@ThreadSafe
public class SynchronizedInteger
{
    @GuardedBy("this")
    private int value;

    public synchronized int getValue()
    {
        return value;
    }

    public synchronized void setValue(int value)
    {
        this.value = value;
    }
}
