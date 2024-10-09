package in.ushatech;

import java.util.HashSet;
import java.util.Set;

public class EscapedObjectsExample
{
    // Since this is public and can be accessed even before class instantiation and is published
    // before initialize method is run , it is escaped object
    // Publishing one object may publish other objects also
    public static Set<Secret> knownSecrets;

    public void initialize()
    {
        knownSecrets = new HashSet<>();
    }
}
