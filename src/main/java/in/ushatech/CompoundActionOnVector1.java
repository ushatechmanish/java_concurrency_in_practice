package in.ushatech;

import in.ushatech.annotation.ThreadSafe;

import java.util.Vector;

@ThreadSafe
public class CompoundActionOnVector1
{
    public static Object getLast(Vector list)
    {
        synchronized (list)//intrinsic lock
        {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }

    }

    public static Object deleteLast(Vector list)
    {
        synchronized (list) //intrinsic lock
        {
            int lastIndex = list.size() - 1;
            return list.remove(lastIndex);
        }

    }
}
