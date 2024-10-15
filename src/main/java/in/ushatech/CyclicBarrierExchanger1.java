package in.ushatech;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExchanger1
{

    public static void main(String[] args)
    {
        final SharedBuffer[] buffer1 = {new SharedBuffer("Buffer1: ")};
        final SharedBuffer[] buffer2 = {new SharedBuffer("Buffer2: ")};

        CyclicBarrier barrier = new CyclicBarrier(2, () ->
        {
            // This runs after both threads reach the barrier: Exchange the buffers
            SharedBuffer temp = buffer1[0];
            buffer1[0] = buffer2[0];
            buffer2[0] = temp;
            System.out.println("Buffers exchanged!");
        });

        Worker worker1 = new Worker(buffer1[0], barrier);
        Worker worker2 = new Worker(buffer2[0], barrier);

        Thread t1 = new Thread(worker1, "Worker1");
        Thread t2 = new Thread(worker2, "Worker2");

        t1.start();
        t2.start();
    }

    // Shared resource buffers
    private static class SharedBuffer
    {
        private StringBuffer buffer;

        public SharedBuffer(String name)
        {
            buffer = new StringBuffer(name);
        }

        public void append(char c)
        {
            buffer.append(c);
        }

        public String getBuffer()
        {
            return buffer.toString();
        }

        public void setBuffer(StringBuffer buffer)
        {
            this.buffer = buffer;
        }
    }

    // Worker that works on its own buffer and then exchanges it
    static class Worker implements Runnable
    {
        private final CyclicBarrier barrier;
        private SharedBuffer buffer;

        public Worker(SharedBuffer buffer, CyclicBarrier barrier)
        {
            this.buffer = buffer;
            this.barrier = barrier;
        }

        @Override
        public void run()
        {
            try
            {
                for (int i = 0; i < 5; i++)
                {
                    // Simulate work by appending to buffer
                    buffer.append(Thread.currentThread().getName().charAt(0));

                    // Wait for the other thread to finish
                    System.out.println(Thread.currentThread().getName() + " before barrier: " + buffer.getBuffer());

                    barrier.await(); // Synchronize with the other thread

                    // After barrier: buffers are exchanged
                    System.out.println(Thread.currentThread().getName() + " after barrier: " + buffer.getBuffer());
                }
            } catch (InterruptedException | BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        }

        // Method to change the buffer (ownership transfer)
        public void setBuffer(SharedBuffer buffer)
        {
            this.buffer = buffer;
        }
    }
}
