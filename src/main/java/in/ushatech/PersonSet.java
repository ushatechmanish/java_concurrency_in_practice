package in.ushatech;

import in.ushatech.annotation.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

// As we are not publishing the set reference . This is thread safe as addition and checking of the person
// are synchronized
@ThreadSafe
public class PersonSet
{
    private final Set<Person> mySet = new HashSet<>();

    // The issue here is that person added in not thread safe and can be modified by
    // other thread
    // Confinement makes it easier to build thread safe classes because a class that
    // confines its state can be analyzed for thread safety without having to examine the whole program
    public synchronized void addPerson(Person person)
    {
        mySet.add(person);
    }

    public synchronized boolean contains(Person person)
    {
        return mySet.contains(person);
    }

}
