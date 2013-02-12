package com.semperos.screwdriver.server;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.proxy.ProxyServlet;

@SuppressWarnings("serial")
public class RedirectingProxyServlet extends ProxyServlet
{
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    public void service(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {
        super.service(request, response);
    }

    @Override
    protected URI rewriteURI(HttpServletRequest request)
    {
        String proxyTo = getProxyTo(request);
        if (proxyTo == null) return null;
        String proxyPath = proxyTo + "/";
        
        String query = request.getQueryString();
        if (query != null) proxyPath += "?" + query;
        
        URI newUri = URI.create(proxyPath).normalize();
        System.out.println("Redirecting to " + newUri);
        return newUri;
    }

    // here, set up rules based on the details of the request
    private String getProxyTo(HttpServletRequest request)
    {
        return "http://localhost:8100/redirect";
    }
}