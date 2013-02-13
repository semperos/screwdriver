package com.semperos.screwdriver.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class LocalServlet extends HttpServlet
{
    private static final Logger logger = Logger.getLogger(LocalServlet.class);
    private final String jspPath;
    

    public LocalServlet(String jspPath)
    {
        this.jspPath = jspPath;
        logger.info("Servlet started");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getRequestURI());
        request.getRequestDispatcher(jspPath).forward(request, response);
    }
}