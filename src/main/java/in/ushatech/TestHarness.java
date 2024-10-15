package in.ushatech;

import java.util.concurrent.CountDownLatch;

public class TestHarness
{
    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException
    {
        final CountDownLatch start = new CountDownLatch(1);// latch  used by worker threads to wait till the start latch is unlatched by main thread
        final CountDownLatch end = new CountDownLatch(nThreads); // latch used by main thread to wait till all worker threads are done and countdown the

        for (int i = 0; i < nThreads; i++)
        {
            Thread thread = new Thread(
                    () ->
                    {
                        try
                        {
                            start.await(); // worker thread wait for signal from main thread to start .
                            try
                            {
                                task.run();
                            } finally
                            {
                                end.countDown(); // convey to the main thread that one worker thread is done
                            }
                        } catch (InterruptedException ignored)
                        {
                            // not a good practice to ignore the interrupted exception
                        }
                    }
            );
            thread.start();
        }
        long startTime = System.currentTimeMillis();
        start.countDown(); // signal all worker thread to start the work
        end.await(); // main thread will wait till all worker threads are done
        long endTime = System.currentTimeMillis();
        return endTime - startTime; // for all the task
    }

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println(timeTasks(10, () ->
        {
            int sum = 0;
            for (int i = 0; i < 10000; i++)
            {
                sum += i;
            }
        }));
    }
}
