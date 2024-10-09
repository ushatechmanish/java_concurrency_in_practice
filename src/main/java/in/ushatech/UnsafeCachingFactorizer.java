package in.ushatech;

import in.ushatech.annotation.NotThreadSafe;
import in.ushatech.annotation.ThreadSafe;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class UnsafeCachingFactorizer extends AbstractServlet
{
    // This can be atomically updated
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();

    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException
    {
        BigInteger i = extractFromRequest(req);

        if(i.equals(lastNumber.get())) {
            encodeIntoResponse(resp,lastFactors.get());
            return;
        }

        BigInteger[] factors = factor(i); // Even though it is thread safe now but
        // 2 separate process can calculate the factor for same number.
        // So it is duplicacy of efforts .
        // Better alternative would be to check if factors for i is being calculated
        // now . if yes wait for it to complete and do not calculate the factors again
        synchronized (this)
        {
            lastNumber.set(i);
            lastFactors.set(factors);
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
