package in.ushatech;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollection
{
    public static void main(String[] args)
    {
        // This uses lock striping . ideally dividing the hash in 16 locks individually
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

        // ConcurrentMap methods


        // if null is returned by the remapping function then the entry is removed from the map
        // if non null value is returned from the remapping function the value is entered
        map.compute("3", (k, v) -> v == null ? "" : v + "1");


//        remove(Object key, Object value)
//        Removes the entry for a key only if it is currently mapped to a specific value.

//        replace(K key, V oldValue, V newValue)
//        Replaces the entry for a key only if currently mapped to a given value.

//        replace(K key, V value)
//        Replaces the entry for a key only if it is currently mapped to some value.

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        list.addIfAbsent("5");
        list.addAllAbsent(Arrays.asList("5", "7"));
        System.out.println(list);

        // difference between iterator and ListIterator
        // ListIterator provides forward and backward and provide option to insert set and remove operations

//        BlockingQueue Implementations
//        ArrayBlockingQueue:
//        A bounded blocking queue backed by an array. Elements are ordered in FIFO (First-In-First-Out) order.
//            LinkedBlockingQueue:
//        An optionally bounded blocking queue backed by linked nodes. It can be either bounded or unbounded.
//            PriorityBlockingQueue:
//        An unbounded blocking queue where elements are ordered according to their natural ordering or by a Comparator.
//            SynchronousQueue:
//        A queue where each insert operation must wait for a corresponding remove operation. It has no internal capacity.
//            DelayQueue:
//        A blocking queue where elements can only be taken when their delay has expired.
//        LinkedTransferQueue:
//        A blocking queue with an unbounded capacity where producers may wait for consumers to receive elements.

//        Summary of Key Points:
//        BlockingQueue: Provides thread-safe queue operations with blocking for concurrent scenarios.
//        Implementations: Includes ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue, SynchronousQueue, DelayQueue, etc.
//        Blocking methods: put(), take(), offer(), poll(), drainTo(), etc., to handle different levels of concurrent access and waiting.
//

        
    }
}
