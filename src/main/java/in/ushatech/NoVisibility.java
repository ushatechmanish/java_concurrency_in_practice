package in.ushatech;

// Weird things can happen in multithreaded environment if
public class NoVisibility
{
    private static boolean ready; // stale data
    private static int number;// stale data

    private static class ReaderThread extends Thread
    {
        public void run()
        {
            while(!ready) // potential for infinite loops
            {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args)
    {
        new ReaderThread().start();
        number = 42 ;// line 1
        ready = true;// line 2 ready might never be visible to the thread if the cache saves the false
        // value for ready
        // order of line 1 and line 2 is not guaranteed
        // cache , reordering can occur
    }
}
