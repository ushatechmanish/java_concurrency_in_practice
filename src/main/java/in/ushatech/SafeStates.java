package in.ushatech;

public class SafeStates
{
    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates()
    {
        return states.clone();// this will not publish the states
        // correct way is to share a clone of the states as sharing the copy
        // will not make changes to the object inside the array
        // Only caution is to decide between shallow or deep clone
    }
}
