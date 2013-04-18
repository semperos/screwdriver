package com.semperos.screwdriver.server.example;

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
        server.setEnabledDirectoryListing(false);
        server.setLocalResourcesBasePath("./WEB-INF/jsp");
        server.setLocalContextPath("/local");
        server.addLocalServlet("testpage.jsp", "/servlet");
        
        // --- proxy servlet ---
        server.addProxyServlet("http://localhost:8100/redirect", "/redirect/*");

        server.start();
    }
}