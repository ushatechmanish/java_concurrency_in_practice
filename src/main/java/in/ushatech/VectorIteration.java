package in.ushatech;

import java.util.Vector;

public class VectorIteration
{
    public void threadUnsafeIteration(Vector list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }

    public void threadSafeIteration(Vector list)
    {
        synchronized (list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.println(list.get(i));
            }
        }
    }
}
