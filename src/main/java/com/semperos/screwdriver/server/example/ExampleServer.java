package com.semperos.screwdriver.server.example;

import com.semperos.screwdriver.server.ScrewdriverServer;
import com.semperos.screwdriver.server.utils.LocalServlet;

public class ExampleServer
{
    public static void main(String[] args) throws Exception
    {
        ScrewdriverServer server = new ScrewdriverServer(3333);
        
        // --- static files --- 
        server.setStaticResourceBasePath(".");
        server.setEnabledStaticResourceFallthrough(true);
        
        // --- local servlet ---
        LocalServlet servlet = new LocalServlet("testpage.jsp");
        server.addLocalServlet(servlet, "/local", "/servlet");
        server.setEnabledDirectoryListing(false);
        
        server.setLocalResourcesBasePath("./WEB-INF/jsp");
        
        // --- proxy servlet ---
        server.addProxyServlet("http://localhost:8100/redirect", "/redirect/*");

        server.start();
    }
}