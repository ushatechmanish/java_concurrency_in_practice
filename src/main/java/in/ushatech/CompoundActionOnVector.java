package in.ushatech;

import in.ushatech.annotation.NotThreadSafe;

import java.util.Vector;

@NotThreadSafe
public class CompoundActionOnVector
{
    public static Object getLast(Vector list)
    {
        int lastIndex = list.size()-1;
        return list.get(lastIndex);
    }
    public static Object deleteLast(Vector list)
    {
        int lastIndex = list.size()-1;
        return list.remove(lastIndex);
    }
}
