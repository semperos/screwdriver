package com.semperos.screwdriver.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/6/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Parameters(commandDescription = "Run the Screwdriver build tool")
public class CommandMain {
    @Parameter(names = { "-d", "--debug" }, description = "Debug mode")
    public boolean debug = false;

    @Parameter(names = { "-h", "--help" }, help = true)
    public boolean help;

    @Parameter(names = { "-b", "--base-path" }, description = "The base path from which to calculate asset paths")
    public String basePath = System.getProperty("user.dir");

}
