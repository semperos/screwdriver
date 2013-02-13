package com.semperos.screwdriver.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class FakeRemoteServer
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8100);
        server.setConnectors(new Connector[] {connector});
        
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setWar("./WEB-INF/jsp/");
        webAppContext.addServlet(new ServletHolder(new ResponseServlet()), "/redirect/*");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { webAppContext });
        
        server.setHandler(handlers);
        server.start();
        server.join();
    }

    @SuppressWarnings("serial")
    public static class ResponseServlet extends HttpServlet
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
        {
            try
            {
                request.getRequestDispatcher("/redirect.jsp")
                       .forward(request, response);
            }
            catch (Throwable e1)
            {
                e1.printStackTrace();
            }
        }
    }
}