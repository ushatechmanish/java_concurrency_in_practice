package in.ushatech;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet
{
    Set<Integer> set;
    Semaphore semaphore;

    public BoundedHashSet(int bound)
    {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(Integer element) throws InterruptedException
    {
        semaphore.acquire(); // to limit adding new element if semaphore is not available
        // it will block on this .
        boolean wasAdded = false;
        try
        {
            wasAdded = set.add(element);
            return wasAdded;
        } finally
        {
            if (!wasAdded) // if there was some error and the element was not added
                // we can release the sempahore so that other threads can add the resource
                semaphore.release();
        }


    }

    public boolean remove(Object o)
    {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
        {
            semaphore.release(); // if the remove is successful , allow other threads to aquire resources
        }
        return wasRemoved;
    }
}
