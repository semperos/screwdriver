package com.semperos.screwdriver.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class MainServer
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.setConnectors(new Connector[] {connector});

        // --- context for locally-hosted servlets ---
        
        String warPath = "./local/jsp";
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar(warPath);
        webAppContext.addServlet(new ServletHolder(new LocalServlet()), "/local/*");
        
        // disallow directory listing
        webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");

        // --- context for remote redirection ---
        
        ServletContextHandler proxiedServlets = new ServletContextHandler(ServletContextHandler.SESSIONS);
        proxiedServlets.setContextPath("/");
        proxiedServlets.addServlet(new ServletHolder(new RedirectingProxyServlet()), "/redirect/*");
        
 
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{proxiedServlets, webAppContext});
        server.setHandler(handlers);

        server.start();
        server.join();
    }

    @SuppressWarnings("serial")
    public static class LocalServlet extends HttpServlet
    {
        private static final Logger logger = Logger.getLogger(LocalServlet.class);
        

        public LocalServlet()
        {
            logger.info("Servlet started");
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            request.getRequestDispatcher("/testpage.jsp").forward(request, response);
        }
    }
}