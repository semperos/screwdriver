package com.semperos.screwdriver.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.File;
import java.util.ArrayList;

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

    @Parameter(names = { "-ijs", "--include-js" },
            description = "A comma-separate list of patterns of files to white-list for compilation to JavaScript.",
            variableArity = true)
    public ArrayList<String> jsIncludes = new ArrayList<String>();

    @Parameter(names = { "-icss", "--include-css" },
            description = "A comma-separate list of patterns of files to white-list for compilation to CSS.",
            variableArity = true)
    public ArrayList<String> cssIncludes = new ArrayList<String>();

    @Parameter(names = { "-iimage", "--include-image" },
            description = "A comma-separate list of patterns of files to white-list for processing as images.",
            variableArity = true)
    public ArrayList<String> imageIncludes = new ArrayList<String>();
}
