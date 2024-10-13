package in.ushatech;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable
{
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File file;

    public FileCrawler(BlockingQueue<File> queue, FileFilter fileFilter, File file)
    {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.file = file;
    }

    @Override
    public void run()
    {
        try
        {
            crawl(file);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File file) throws InterruptedException
    {
        File[] entries = file.listFiles(fileFilter);
        for (File entry : entries)
        {
            if (entry.isDirectory())
            {
                crawl(entry);
                continue;
            }
            if (!isIndexed(entry))
                queue.put(entry);
        }
    }

    private boolean isIndexed(File entry)
    {
        // check if the file is indexed or not
        return true;
    }
}
