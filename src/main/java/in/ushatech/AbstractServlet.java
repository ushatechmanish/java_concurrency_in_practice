package in.ushatech;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class AbstractServlet implements Servlet
{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException
    {

    }

    @Override
    public ServletConfig getServletConfig()
    {
        return null;
    }

    @Override
    public String getServletInfo()
    {
        return "";
    }

    @Override
    public void destroy()
    {

    }
}
