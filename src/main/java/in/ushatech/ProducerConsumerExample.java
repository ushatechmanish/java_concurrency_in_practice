package in.ushatech;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExample
{
    public static void main(String[] args)
    {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // Producer thread
        Thread producer = new Thread(() ->
        {
            try
            {
                queue.put("Item 1");
                System.out.println("Produced Item 1");
                queue.put("Item 2");
                System.out.println("Produced Item 2");
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() ->
        {
            try
            {
                System.out.println("Consumed " + queue.take());
                System.out.println("Consumed " + queue.take());
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
