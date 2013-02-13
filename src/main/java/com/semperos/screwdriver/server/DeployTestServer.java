package com.semperos.screwdriver.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class DeployTestServer
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8888);
        
        WebAppContext servletContextHandler = new WebAppContext();
        servletContextHandler.setWar("/home/kirill/base/projects/screwdriver/target/screwdriver-0.0.1-SNAPSHOT.war");
        server.setHandler(servletContextHandler);
        
        server.start();
        server.join();
    }
}