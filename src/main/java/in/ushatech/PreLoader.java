package in.ushatech;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PreLoader
{
    FutureTask<ProductInfo> task = new FutureTask<ProductInfo>(() ->
    {
        return loadProductInfo();
    });
    Thread thread = new Thread(task);

    private ProductInfo loadProductInfo()
    {
        // Some heavy computation
        return new ProductInfo();
    }

    public void start()
    {
        thread.start();
    }

    public ProductInfo get() throws InterruptedException, DataLoadException
    {
        try
        {
            return task.get();
            // this can throw checked exception or error or runtime exception
            // to manage these we encapsulate it in launderthrowable message.
        } catch (ExecutionException e)
        {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) // throw checked known exceptions
            {
                throw (DataLoadException) cause;
            }
            throw launderThrowable(cause);
        }
    }

    private DataLoadException launderThrowable(Throwable cause)
    {
        if (cause instanceof RuntimeException)
        {
            throw (RuntimeException) cause;
        }
        if (cause instanceof Error)
        {
            throw (Error) cause;
        }
        // checked exception
        throw new IllegalStateException("checked exception", cause);
    }

}
