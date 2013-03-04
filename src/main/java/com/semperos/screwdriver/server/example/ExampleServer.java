package com.semperos.screwdriver.server.example;

import org.eclipse.jetty.servlet.ServletContextHandler;

import com.semperos.screwdriver.server.ScrewdriverServer;

public class ExampleServer
{
    public static void main(String[] args) throws Exception
    {
        ScrewdriverServer server = new ScrewdriverServer(8000);
        
        // --- static files --- 
        server.setResourceBasePath(".");
        server.setEnabledDirectoryListing(false);
        
        // --- local servlet ---
        server.setLocalResourcesBasePath("./WEB-INF/jsp/");
        server.addLocalServlet("/testpage.jsp", "/local/*");
        
        // --- proxy servlet ---
        server.addProxyServlet("http://localhost:8100/redirect", "/redirect/*");
        ServletContextHandler proxiedServlets = new ServletContextHandler(ServletContextHandler.SESSIONS);
        proxiedServlets.setContextPath("/");

        server.start();
    }
}