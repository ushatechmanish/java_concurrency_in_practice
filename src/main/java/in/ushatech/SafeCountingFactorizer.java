package in.ushatech;

import in.ushatech.annotation.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class SafeCountingFactorizer extends AbstractServlet
{
    // If this is used to generate sequences then it is a big issue and can cause serious data integrity issues
    //    private long count;
    private AtomicLong count = new AtomicLong(0) ;

    public long getCount()
    {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException
    {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp,factors);
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors)
    {
    }

    private BigInteger[] factor(BigInteger i)
    {
        return null;
    }

    private BigInteger extractFromRequest(ServletRequest req)
    {
        return new BigInteger("12323423");
    }
}
