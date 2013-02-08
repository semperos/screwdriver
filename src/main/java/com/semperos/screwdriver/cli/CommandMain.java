package com.semperos.screwdriver.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.File;

/**
 *
 */
@Parameters(commandDescription = "Run the Screwdriver build tool")
public class CommandMain {
    @Parameter(names = { "-d", "--debug" }, description = "Debug mode")
    public boolean debug = false;

    @Parameter(names = { "-h", "--help" }, help = true)
    public boolean help;

    @Parameter(names = { "-a", "--asset-directory" }, description = "The base path from which to calculate asset paths")
    public String assetDirectory = System.getProperty("user.dir");

    @Parameter(names = { "-o", "--output-directory" }, description = "The base path from which to calculate where to write processed assets")
    public String outputDirectory = System.getProperty("user.dir") + File.separator + "target" + File.separator + "client";

}
