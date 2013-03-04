package com.semperos.screwdriver.server.utils;

import javax.servlet.http.HttpServletRequest;

public class ServerUtils
{
    @SuppressWarnings("serial")
    public static RemoteProxyServlet proxyServletFromUrl(final String redirectionUrl)
    {
        return new RemoteProxyServlet()
        {
            @Override
            protected String getProxyTo(HttpServletRequest request)
            {
                return redirectionUrl;
            }
        };
    }
}