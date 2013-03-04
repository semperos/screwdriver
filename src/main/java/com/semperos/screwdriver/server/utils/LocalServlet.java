package com.semperos.screwdriver.server.utils;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class LocalServlet extends HttpServlet
{
    private static final Logger logger = Logger.getLogger(LocalServlet.class);
    private final String pagePath;

    public LocalServlet(String pagePath)
    {
        this.pagePath = pagePath;
        logger.info("[" + pagePath + "] Servlet started");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher(pagePath).forward(request, response);
    }
}