package com.semperos.screwdriver.server.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.eclipse.jetty.proxy.ProxyServlet;

@SuppressWarnings("serial")
public abstract class RemoteProxyServlet extends ProxyServlet
{
    private static Logger logger = Logger.getLogger(RemoteProxyServlet.class);

    private final boolean urlEncodeQuery;

    
    public RemoteProxyServlet()
    {
        this(false);
    }

    /**
     * @param urlEncodeQuery whether or not to URL-encode the 
     * redirected query using {@link java.net.URLEncoder}.
     */
    public RemoteProxyServlet(boolean urlEncodeQuery)
    {
        this.urlEncodeQuery = urlEncodeQuery;
    }

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    public void service(ServletRequest request, ServletResponse response) throws IOException, ServletException
    {
        super.service(request, response);
    }
    
    public String urlEncodeQuery(String query)
    {
        try
        {
            // The query is an ampersand-delimited sequence of key=value
            // pairs.  The values have to be properly URL-encoded.
            String[] kvPairs = query.split("&");
            StringBuilder encoded = new StringBuilder();
            
            for (String kvPair : kvPairs)
            {
                int equalsLoc = kvPair.indexOf('=');
                String key = kvPair.substring(0, equalsLoc);
                String value = kvPair.substring(equalsLoc+1);
                
                String encodedValue = URLEncoder.encode(value, "UTF8");
                
                if (encoded.length() > 0) encoded.append("&");
                encoded.append(key).append("=").append(encodedValue);
            }
            return encoded.toString();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to UTF8-encode URI from " + query);
        }
    }

    @Override
    protected URI rewriteURI(HttpServletRequest request)
    {
        String proxyTo = getProxyTo(request);
        if (proxyTo == null) return null;

        String proxyPath = proxyTo + request.getRequestURI();
        String query = request.getQueryString();
        
        if (query != null) 
        {
            if (urlEncodeQuery) query = urlEncodeQuery(query);
            proxyPath += "?" + query;
        }

        try
        {
            URI newUri = URI.create(proxyPath);
            logger.trace("Redirecting to " + newUri);
            return newUri;
        }
        catch (IllegalArgumentException e)
        {
            String m = e.getMessage();
            int index = Integer.parseInt(m.substring(m.indexOf("index") + 6, m.indexOf(":")));
            String s = m.substring(m.indexOf(":") + 1).trim();
            char c = s.charAt(index);
            
            e.printStackTrace();
            throw new RuntimeException("Failed to redirect URI due to illegal character '" + c +
                                        " at index " + index + " in the query: " + m);
        }
    }

    /** Here, set up rules based on the details of the request **/
    protected abstract String getProxyTo(HttpServletRequest request);
}