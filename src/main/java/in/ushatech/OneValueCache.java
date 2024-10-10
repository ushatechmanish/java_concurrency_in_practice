package in.ushatech;

import in.ushatech.annotation.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

@Immutable
public class OneValueCache
{

    private BigInteger lastNumber;

    private BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors)
    {
        this.lastNumber = lastNumber;
        this.lastFactors = lastFactors;
    }

    public BigInteger[] getFactors(BigInteger i)
    {
        if (i == null || !i.equals(lastNumber)) return null;

        return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
