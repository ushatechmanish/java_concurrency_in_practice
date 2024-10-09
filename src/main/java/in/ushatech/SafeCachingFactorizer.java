package in.ushatech;

import in.ushatech.annotation.GuardedBy;
import in.ushatech.annotation.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

// Every variable and operation on it should be guided by the same lock
@ThreadSafe
public class SafeCachingFactorizer extends AbstractServlet
{
    // This can be atomically updated
    @GuardedBy("this")
    private  BigInteger lastNumber ;
    @GuardedBy("this")
    private  BigInteger[] lastFactors ;
    @GuardedBy("this")
    private long hits;
    @GuardedBy("this")
    private long cacheHits;

    private synchronized long getHits()
    {
        return hits;
    }
    public synchronized double getCacheHitsRatio()
    {
        return cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException
    {
        BigInteger i = extractFromRequest(req);

        BigInteger[] factors = null; // local variable we can do whatever we want with this

        synchronized (this)
        {
            ++hits;
            if(i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();// clone to avoid changing the cache value later
                // as we should not pass the reference of mutable object lastFactors
                // now we are in synchronized block , after this block there is no guarantee
                // that lastFactors will not change or we change it in out code
                // any change in our code should not affect the correct lastFactors
            }
        }

        if(factors == null) {
            // Even though it is thread safe now but
            // 2 separate process can calculate the factor for same number.
            // So it is duplicacy of efforts .
            // Better alternative would be to check if factors for i is being calculated
            // now . if yes wait for it to complete and do not calculate the factors again
            factors = factor(i); // long running operation avoid in synchronized block
            synchronized (this) {
                lastNumber = i;
                lastFactors =   factors.clone();// clone to avoid
            }
        }

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
