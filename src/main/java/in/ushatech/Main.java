package in.ushatech;

public class Main
{
    /**
     * 1. Do not share the state variables across threads
     * 2. Make the state variable immutable
     * 3. Use synchronization whenever accessing the state variable
     * */
    public static void main(String[] args)
    {
        Thread t = new Thread(
                () -> System.out.println("New Thread is Running"));
        t.start();



    }
}