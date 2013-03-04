package com.semperos.screwdriver.server;

import static com.semperos.screwdriver.server.utils.ServerUtils.proxyServletFromUrl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.semperos.screwdriver.server.utils.LocalServlet;

public class ScrewdriverServer
{
    private final int port;
    
    private final Map<String, Servlet> localServletsByRoute = new LinkedHashMap<>();
    private final Map<String, Servlet> proxyServletsByRoute = new LinkedHashMap<>();

    private String staticResourceBasePath = ".";
    private String localResourcesBasePath = ".";
    private boolean enableDirectoryListing = false;
    

    public ScrewdriverServer(int port)
    {
        this.port = port;
    }
    
    public void setResourceBasePath(String path)
    {
        this.staticResourceBasePath = path;
    }
    
    public void setLocalResourcesBasePath(String path)
    {
        this.localResourcesBasePath = path;
    }
    
    public void addLocalServlet(String resourcePath, String route)
    {
        addLocalServlet(new LocalServlet(resourcePath), route);
    }
    
    public void addLocalServlet(Servlet servlet, String route)
    {
        localServletsByRoute.put(route, servlet);
    }
    
    public void addProxyServlet(String redirectionUrl, String route)
    {
        addProxyServlet(proxyServletFromUrl(redirectionUrl), route);
    }
    
    public void addProxyServlet(Servlet servlet, String route)
    {
        proxyServletsByRoute.put(route, servlet);
    }
    
    public void setEnabledDirectoryListing(boolean enabled)
    {
        this.enableDirectoryListing = enabled;
    }
    
    public void start()
    {
        Server server = new Server(port);
        
        ResourceHandler staticResourceHandler = new ResourceHandler();
        staticResourceHandler.setDirectoriesListed(enableDirectoryListing);
        staticResourceHandler.setResourceBase(staticResourceBasePath);
        
        WebAppContext localServletHandler = new WebAppContext();
        localServletHandler.setWar(localResourcesBasePath);
        registerServlets(localServletsByRoute, localServletHandler);
        
        localServletHandler.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", 
                                             Boolean.toString(enableDirectoryListing));

        // --- context for remote redirection ---
        ServletContextHandler proxyServletsHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        proxyServletsHandler.setContextPath("/");
        registerServlets(proxyServletsByRoute, proxyServletsHandler);
         
        for (Entry<String, Servlet> servletSpec : localServletsByRoute.entrySet())
        {
            String route = servletSpec.getKey();
            Servlet servlet = servletSpec.getValue();
	        localServletHandler.addServlet(new ServletHolder(servlet), route);
        }

        // proxied routes, then local routes, then static resource handler 
        HandlerCollection handlerList = new HandlerCollection();
        handlerList.setHandlers(new Handler[] {proxyServletsHandler, localServletHandler, staticResourceHandler});
        server.setHandler(handlerList);

        try
        {
            server.start();
            server.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to start server.");
        } 
    }
    
    private static void registerServlets(Map<String, Servlet> servletsByRoute, ServletContextHandler handler)
    {
        for (Entry<String, Servlet> servletSpec : servletsByRoute.entrySet())
        {
            String route = servletSpec.getKey();
            Servlet servlet = servletSpec.getValue();
            handler.addServlet(new ServletHolder(servlet), route);
        }
    }
}