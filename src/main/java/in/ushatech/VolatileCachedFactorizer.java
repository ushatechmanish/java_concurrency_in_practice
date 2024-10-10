package in.ushatech;

import in.ushatech.annotation.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

// Every variable and operation on it should be guided by the same lock
@ThreadSafe
public class VolatileCachedFactorizer extends AbstractServlet
{
    // This can be atomically updated
    // Extract the variables in an immutable object .

    // Holder object for changing state variables
    private volatile OneValueCache cache = new OneValueCache(null, null);

    //    @GuardedBy("this")
//    private BigInteger lastNumber;
//    @GuardedBy("this")
//    private BigInteger[] lastFactors;

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException
    {
        BigInteger i = extractFromRequest(req);

        BigInteger[] factors = cache.getFactors(i); // local variable we can do whatever we want with this

        if (factors == null)
        {
            // Even though it is thread safe now but
            // 2 separate process can calculate the factor for same number.
            // So it is duplicacy of efforts .
            // Better alternative would be to check if factors for i is being calculated
            // now . if yes wait for it to complete and do not calculate the factors again
            factors = factor(i); // long running operation avoid in synchronized block
            cache = new OneValueCache(i, factors); // replacing the cache value at once . This will not affect other
            // threads as they will access to cache object at that time and this is a single call
        }

        encodeIntoResponse(resp, factors);
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
