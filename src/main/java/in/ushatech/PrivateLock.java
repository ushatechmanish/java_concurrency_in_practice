package in.ushatech;

import in.ushatech.annotation.GuardedBy;

public class PrivateLock
{
    // client can not access the lock so it adds an advantage
    private final Object myLock = new Object();

    // Not giving access to the intrinsic lock avoid deadlocks

    @GuardedBy("myLock")
    Widget widget;

    void someMethod()
    {
        synchronized (myLock)
        {
            // make some changes to widget.
        }
    }
}
