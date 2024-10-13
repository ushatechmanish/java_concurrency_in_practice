package in.ushatech;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyProducerConsumer
{
    public static void main(String[] args)
    {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Thread producer = new Thread(
                () ->
                {
                    try
                    {
                        int i = 0;
                        while (true)
                        {
                            queue.put(i++);
                            System.out.println("Next number to be produced is " + i);
                            if (i == 100)
                            {
                                queue.put(-1); // poison pill
                                break;
                            }
                            TimeUnit.SECONDS.sleep(1); // better readability as seconds minutes milliseconds can be mentioned
                        }
                    } catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
        );

        Thread consumer = new Thread(
                () ->
                {
                    try
                    {
                        while (true)
                        {
                            Integer consumed = queue.take();
                            if (consumed == -1) break;// detected poison pill
                            System.out.println("consumed" + consumed);

                        }
                    } catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
        );

        producer.start();
        consumer.start();
    }


}
