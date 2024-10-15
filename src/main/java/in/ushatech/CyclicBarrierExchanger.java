package in.ushatech;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExchanger
{

    public static void main(String[] args)
    {
        BufferHolder bufferHolder1 = new BufferHolder(new SharedBuffer("Buffer1: "));
        BufferHolder bufferHolder2 = new BufferHolder(new SharedBuffer("Buffer2: "));

        CyclicBarrier barrier = new CyclicBarrier(2, () ->
        {
            // Swap the buffers inside the barrier action
            SharedBuffer temp = bufferHolder1.getBuffer();
            bufferHolder1.setBuffer(bufferHolder2.getBuffer());
            bufferHolder2.setBuffer(temp);
            System.out.println("Buffers exchanged!");
        });

        Worker worker1 = new Worker(bufferHolder1, barrier);
        Worker worker2 = new Worker(bufferHolder2, barrier);

        Thread t1 = new Thread(worker1, "Worker1");
        Thread t2 = new Thread(worker2, "Worker2");

        t1.start();
        t2.start();
    }

    // Wrapper to hold the buffer (so that it can be swapped inside the lambda)
    private static class BufferHolder
    {
        private SharedBuffer buffer;

        public BufferHolder(SharedBuffer buffer)
        {
            this.buffer = buffer;
        }

        public SharedBuffer getBuffer()
        {
            return buffer;
        }

        public void setBuffer(SharedBuffer buffer)
        {
            this.buffer = buffer;
        }
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
    }

    // Worker that works on its own buffer and then exchanges it
    static class Worker implements Runnable
    {
        private final CyclicBarrier barrier;
        private BufferHolder bufferHolder;

        public Worker(BufferHolder bufferHolder, CyclicBarrier barrier)
        {
            this.bufferHolder = bufferHolder;
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
                    bufferHolder.getBuffer().append(Thread.currentThread().getName().charAt(0));

                    // Wait for the other thread to finish
                    System.out.println(Thread.currentThread().getName() + " before barrier: " + bufferHolder.getBuffer().getBuffer());

                    barrier.await(); // Synchronize with the other thread

                    // After barrier: buffers are exchanged
                    System.out.println(Thread.currentThread().getName() + " after barrier: " + bufferHolder.getBuffer().getBuffer());
                }
            } catch (InterruptedException | BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        }
    }
}
