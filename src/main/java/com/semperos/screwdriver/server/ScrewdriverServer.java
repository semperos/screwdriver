package com.semperos.screwdriver.server;

import static com.semperos.screwdriver.server.utils.ServerUtils.proxyServletFromUrl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class ScrewdriverServer
{
    private final int port;
    
    private final List<ContextHandler> localServletHandlers = new ArrayList<>();
    private final Map<String, Servlet> proxyServletsByRoute = new LinkedHashMap<>();

    private String staticResourceBasePath = ".";
    private String localResourcesBasePath = ".";
    private boolean enableDirectoryListing = false;
    
    private boolean enableStaticResourceFallthrough = true;
    

    public ScrewdriverServer(int port)
    {
        this.port = port;
    }
    
    public void setStaticResourceBasePath(String path)
    {
        this.staticResourceBasePath = path;
    }
   
    public void setLocalResourcesBasePath(String path)
    {
        this.localResourcesBasePath = path;
    }
     
    public void setEnabledDirectoryListing(boolean enabled)
    {
        this.enableDirectoryListing = enabled;
    }
     
    public void setEnabledStaticResourceFallthrough(boolean enabled)
    {
        this.enableStaticResourceFallthrough = enabled;
    }
    
    public void addLocalServlet(Servlet servlet, String contextPath, String routePath)
    {
        WebAppContext handler = new WebAppContext();
        if (contextPath != null) handler.setContextPath(contextPath);
        handler.addServlet(new ServletHolder(servlet), routePath);
        localServletHandlers.add(handler);
    }
     
    public void addLocalServlet(String fileResourceName, String contextPath, String routePath)
    {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        
        resourceHandler.setWelcomeFiles(new String[]{fileResourceName});
        ContextHandler handler = new ContextHandler();
        if (contextPath != null) handler.setContextPath(contextPath);
        handler.setHandler(resourceHandler);
        
        localServletHandlers.add(handler);
    }
    
    public void addProxyServlet(String redirectionUrl, String route)
    {
        addProxyServlet(proxyServletFromUrl(redirectionUrl), route);
    }
    
    public void addProxyServlet(Servlet servlet, String route)
    {
        proxyServletsByRoute.put(route, servlet);
    }
   
    public void start()
    {
        Server server = new Server(port);
        
       
        // --- local servlets ---
        String dirAllowed = Boolean.toString(enableDirectoryListing);
        for (ContextHandler handler: localServletHandlers)
        {
            handler.setResourceBase(localResourcesBasePath);
            handler.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", dirAllowed);
        }
                    
        // --- context for remote redirection ---
        ServletContextHandler proxyServletsHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        proxyServletsHandler.setContextPath("/");
        registerServlets(proxyServletsByRoute, proxyServletsHandler);
         
        // proxied routes, then local routes, then static assets
        HandlerList handlerList = new HandlerList();
        handlerList.addHandler(proxyServletsHandler);
        for (ContextHandler handler : localServletHandlers) handlerList.addHandler(handler);
        
        // --- static resources ---
        if (enableStaticResourceFallthrough)
        {
            ResourceHandler staticResourceHandler = new ResourceHandler();
            staticResourceHandler.setDirectoriesListed(enableDirectoryListing);
            staticResourceHandler.setResourceBase(staticResourceBasePath);
            handlerList.addHandler(staticResourceHandler);
        }
        
        
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