package in.ushatech;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileIndexer implements Runnable
{
    private final BlockingQueue<File> queue;

    public FileIndexer(BlockingQueue<File> queue)
    {
        this.queue = queue;
    }


    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                indexFile(queue.take());
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void indexFile(File take)
    {
    }
}
