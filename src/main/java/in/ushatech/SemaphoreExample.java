package in.ushatech;

import java.util.concurrent.Semaphore;

public class SemaphoreExample
{

    // Semaphore with 3 permits (max 3 threads can access the resource simultaneously)
    private static final Semaphore semaphore = new Semaphore(3);

    // Shared resource
    public static void accessResource(int threadNumber)
    {
        try
        {
            // Acquiring permit before accessing the resource
            System.out.println("Thread " + threadNumber + " is waiting for a permit.");
            semaphore.acquire();
            System.out.println("Thread " + threadNumber + " acquired a permit.");

            // Simulating resource access
            System.out.println("Thread " + threadNumber + " is accessing the resource...");
            Thread.sleep(2000);  // Simulate some work

            System.out.println("Thread " + threadNumber + " is releasing the permit.");

        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        } finally
        {
            // Releasing permit after accessing the resource
            semaphore.release();
        }
    }

    public static void main(String[] args)
    {
        // Creating 6 threads to demonstrate semaphore limiting access
        for (int i = 1; i <= 6; i++)
        {
            int threadNumber = i;
            new Thread(() -> accessResource(threadNumber)).start();
        }
    }
}
