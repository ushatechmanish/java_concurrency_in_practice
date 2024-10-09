package in.ushatech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Use case for ThreadLocal is to prevent sharing in designs based on mutable singletons or global variables
 * For example to use a global connection used by different methods may cause issues .
 * Instead make it ThreadLocal so that for each thread a new connection is provided
 *
 * Second use case for the ThreadLocal is
 * When a frequently used operation required a temporary object such as a buffer
 * and wants to avoid reallocating the temporary object on each invocation
 * In that case we can create a Thread local buffer object to avoid garbage collection and creation of
 * buffer objects
 * */
public class ThreadLocalConnection
{
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>()
    {
        public Connection initialValue()
        {
            try
            {
                return DriverManager.getConnection("db_url");
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
    };

    public static Connection getConnection()
    {
        return connectionHolder.get();
    }
}
