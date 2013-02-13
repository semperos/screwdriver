package com.semperos.screwdriver.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class MainServer
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        
        // --- static files handler --- 
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setResourceBase(".");
        
        // --- local servlet handler ---
        WebAppContext servletContextHandler = new WebAppContext();
        servletContextHandler.setWar("./WEB-INF/jsp/");
        servletContextHandler.addServlet(new ServletHolder(new LocalServlet("/testpage.jsp")), "/local/*");
        servletContextHandler.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        
        // --- context for remote redirection ---
        ServletContextHandler proxiedServlets = new ServletContextHandler(ServletContextHandler.SESSIONS);
        proxiedServlets.setContextPath("/");
        proxiedServlets.addServlet(new ServletHolder(new RedirectingProxyServlet()), "/redirect/*");

        HandlerCollection handlerList = new HandlerCollection();
        handlerList.setHandlers(new Handler[]{resourceHandler, proxiedServlets, servletContextHandler});
        server.setHandler(handlerList);
        
        server.start();
        server.join();
    }
}