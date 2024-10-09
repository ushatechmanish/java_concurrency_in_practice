package in.ushatech;

import in.ushatech.annotation.NotThreadSafe;
import in.ushatech.annotation.ThreadSafe;

@ThreadSafe
public class LazyInitRace
{
    private ExpensiveObject instance = null;

    public synchronized ExpensiveObject getInstance()
    {
        if (instance == null)
        {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}
