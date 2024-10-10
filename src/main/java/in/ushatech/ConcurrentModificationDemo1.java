package in.ushatech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcurrentModificationDemo1
{
    public static void main(String[] args)
    {
        demo1();


    }

    public static void demo1()
    {
        // Create an ArrayList
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        // Use a for-each loop, which uses an iterator internally
        for (String element : list)
        {
            System.out.println("Element: " + element);
            if (element.equals("A"))
                list.remove(element); // This will throw ConcurrentModificationException
            // fail fast . it is only a good faith effort
            // a modification count is kept without synchronization , so it may or may not occur
            // as stale value of count may be available
        }
    }

    public static void demo2()
    {
        // Create an ArrayList
        List<Widget> list = Collections.synchronizedList(new ArrayList<>());
        list.add(new Widget());
        list.add(new Widget());
        list.add(new Widget());

        synchronized (list) // this may lead to deadlock as with lock held doSomething is being called
//      The other issue is the performance issue if the list is long
        {
            // Use a for-each loop, which uses an iterator internally
            for (Widget element : list)
            {
                doSomething(element); // This may throw ConcurrentModificationException

            }
        }
    }
    public static void demo3()
    {
        // Create an ArrayList
        List<Widget> list = Collections.synchronizedList(new ArrayList<>());
        list.add(new Widget());
        list.add(new Widget());
        list.add(new Widget());
    // create a copy  but this will also has to be
        // locked for copying  so again the performance issue
        List<Widget> listCopy;
        synchronized (list)
        {
             listCopy = Collections.unmodifiableList(list);// Thread confinement
        }

            // Use a for-each loop, which uses an iterator internally
            for (Widget element : listCopy)
            {
                doSomething(element); // This may throw ConcurrentModificationException

            }
        }

    private static void doSomething(Widget element)
    {
        // can delete or add element
    }
}


