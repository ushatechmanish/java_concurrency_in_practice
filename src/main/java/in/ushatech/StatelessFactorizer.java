package in.ushatech;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class StatelessFactorizer extends AbstractServlet
{
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException
    {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
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
