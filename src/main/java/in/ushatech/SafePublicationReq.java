package in.ushatech;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class SafePublicationReq
{
    /**
     * 1. initialize and object reference from static initializer
     * 2. Storing a reference to it into a volatile field or AtomicReference
     * 3. Storing a reference to it into a final field of a properly constructed object
     * 4. Storing a reference to it into a field that is properly guided by a lock . This needs extra care
     * Using concurrent data structure can help
     * 5. Safely Published effectively immutable objects can be used safely by any thread without additional
     * synchronization
     */

    /*
     * when you publish an object , declare what can be done with the object
     * Options are
     * 1. Thread confinement .
     * 2. Shared read only
     * 3. Shared thread safe
     * 4. Guarded
     * */

    static public Holder holder = new Holder(42); // static and threadsafe
    public final Holder holder1 = new Holder(42); // construction should be proper
    public AtomicReference<Holder> holderRef = new AtomicReference<>(new Holder(42));
    public List<Integer> listOfNumbers = Collections.synchronizedList(new ArrayList<>());
    public List<Integer> listOfNumbers1 = new CopyOnWriteArrayList<>();


    public Set<Integer> setOfNumbers = new CopyOnWriteArraySet<>();
    public Set<Integer> setOfNumbers2 = Collections.synchronizedSet(new HashSet<>());


    public Map<Integer, Integer> mapOfNumbers = Collections.synchronizedMap(new HashMap<>());
    public ConcurrentMap<Integer, Integer> mapOfNumbers1 = new ConcurrentHashMap<>();
    public Hashtable<Integer, Integer> mapOfNumbers2 = new Hashtable<>();
    public Vector<Integer> vectorOfNumbers = new Vector<>();


    public BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
    public BlockingQueue<Integer> bq1 = new SynchronousQueue<>();
    public BlockingQueue<Integer> bq2 = new LinkedBlockingDeque<>();
    public BlockingQueue<Integer> bq3 = new LinkedTransferQueue<>();
    public BlockingQueue<Integer> bq4 = new PriorityBlockingQueue<>();
    public BlockingQueue<Integer> bq5 = new ArrayBlockingQueue<>(5);


    // Map storing the last Login time of each user
    public Map<String, Date> lastLogin = Collections.synchronizedMap(new HashMap<>());

    // if the date is not changed then the above sychronized map is sufficient and no additional synchronization is
    // required


}
