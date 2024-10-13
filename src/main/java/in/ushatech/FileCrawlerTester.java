package in.ushatech;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileCrawlerTester
{
    static final int BOUND = 10;
    static final int N_CONSUMERS = 4;

    public static void startIndexing(File[] roots)
    {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);

        for (File root : roots)
        {
            FileCrawler fileCrawler = new FileCrawler(queue, (file) -> true, root);
            new Thread(fileCrawler).start();
        }


        for (int i = 0; i < N_CONSUMERS; i++)
        {
            FileIndexer indexer = new FileIndexer(queue);
            new Thread(indexer).start();
        }

    }

}
