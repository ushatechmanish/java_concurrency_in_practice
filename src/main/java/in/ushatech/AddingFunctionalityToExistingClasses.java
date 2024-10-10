package in.ushatech;

import in.ushatech.annotation.GuardedBy;
import in.ushatech.annotation.NotThreadSafe;
import in.ushatech.annotation.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class AddingFunctionalityToExistingClasses
{
    // Ways are
    // 1. Class extension
    // 2. Client locking
    // 3. Composition(best approach) - Add through constructor and then synchronize the method on it


}

// Extension is fragile as the synchrnization policy is spread over inheritance tree
// separately maintained classes
// can change in superclass and break the sub class
class BetterVector<E> extends Vector<E>
{
    public synchronized boolean putIfAbsent(E e)
    {
        boolean absent = !contains(e);
        if (absent) add(e);
        return absent;
    }
}

// Client Locking - wrong impl
// Issue with the class is that the list object and method to make change in the list
// are 2 different locks
@NotThreadSafe
class ListHelper<E>
{
    @GuardedBy("lockInSynchronizedClass")
    // this can be directly accessed from external code so can make changes directly
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // only the illusion of thread safety
    @GuardedBy("this")
    public synchronized boolean putIfAbsent(E e)
    {
        boolean absent = !list.contains(e);
        if (absent) list.add(e);
        return absent;
    }


}

// This only works because the Collections.synchronizedList uses intrinsic locking mechanism
// For client locking we should always check if the class supports client locking
@ThreadSafe
class ListHelper1<E>
{
    @GuardedBy("lockInSynchronizedClass")
    // this can be directly accessed from external code so can make changes directly
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // only the illusion of thread safety
    @GuardedBy("list")
    public boolean putIfAbsent(E e)
    {
        synchronized (list)
        {
            boolean absent = !list.contains(e);
            if (absent) list.add(e);
            return absent;
        }

    }


}

/*
 * Use Composition
 * */

class ImprovedList<E>
{
    private final List<E> list;

    public ImprovedList(List<E> list)
    {
        this.list = list;
    }

    // Add methods for interaction
    public synchronized boolean putIfAbsent(E e)
    {
        // if available
        boolean absent = !list.contains(e); // false
        if (absent)
        {
            list.add(e);
        }

        return absent;
    }
}