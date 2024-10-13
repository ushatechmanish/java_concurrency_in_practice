package in.ushatech;

import java.util.concurrent.LinkedTransferQueue;

/*
Explanation:
The producer uses transfer(42), which blocks until the consumer retrieves the item using take().
This ensures immediate hand-off between the producer and the consumer, making LinkedTransferQueue ideal for scenarios where producers and consumers must be tightly synchronized.
When to Use LinkedTransferQueue:
Direct hand-off between producers and consumers is required.
You want to avoid busy waiting (spinning or polling) and prefer efficient blocking mechanisms.
Suitable for real-time systems or systems with high concurrency where latency needs to be minimal.
Comparison to Other BlockingQueues:
LinkedBlockingQueue: Elements are simply enqueued and dequeued without requiring immediate transfer.
SynchronousQueue: It requires an immediate hand-off between a producer and consumer, but it blocks both unless both are ready.
ArrayBlockingQueue: A bounded queue with a fixed capacity, unlike LinkedTransferQueue, which is unbounded.
* */
public class TransferQueueExample
{
    public static void main(String[] args) throws InterruptedException
    {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();

        // Consumer thread
        Thread consumer = new Thread(() ->
        {
            try
            {
                // Consumer waits for an element
                System.out.println("Consumed: " + queue.take());
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        // Producer thread
        Thread producer = new Thread(() ->
        {
            try
            {
                System.out.println("Producing item...");
                queue.transfer(42);  // Will block until consumer takes it
                System.out.println("Item transferred.");
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        });

        consumer.start();
        Thread.sleep(2000); // Simulate some delay
        producer.start();

        producer.join();
        consumer.join();
    }
}
