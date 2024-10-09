package in.ushatech;

public class NoVisibility
{
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread
    {
        public void run()
        {
            while(!ready)
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
        ready = true;// line 2
        // order of line 1 and line 2 is not guaranteed
        // cache , reordering can occur
    }
}
