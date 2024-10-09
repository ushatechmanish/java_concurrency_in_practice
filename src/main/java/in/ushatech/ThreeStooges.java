package in.ushatech;

import in.ushatech.annotation.Immutable;

import java.util.HashSet;
import java.util.Set;

@Immutable
public final class ThreeStooges
{
    // set is mutable , the design makes it immutable
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges()
    {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name)
    {
        return stooges.contains(name);
    }
}
