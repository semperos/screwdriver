package com.semperos.screwdriver.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.semperos.screwdriver.DefaultConfig;

import java.util.ArrayList;

/**
 *
 */
@Parameters(commandDescription = "Run the Screwdriver build tool")
public class CommandMain {
    @Parameter(names = { "-c", "--config" }, description = "Configuration file for Screwdriver")
    public String config;

    @Parameter(names = { "-s", "--script" }, description = "Use the specified script to execute your build")
    public String script;

    @Parameter(names = { "-d", "--debug" }, description = "Debug mode")
    public boolean debug = DefaultConfig.getDebug();

    @Parameter(names = { "-h", "--help" }, help = true)
    public boolean help;

    @Parameter(names = { "-a", "--asset-directory" }, description = "The base path from which to calculate asset paths")
    public String assetDirectory = DefaultConfig.getAssetDirectory();

    @Parameter(names = { "-o", "--output-directory" }, description = "The base path from which to calculate where to write processed assets")
    public String outputDirectory = DefaultConfig.getOutputDirectory();

    @Parameter(names = { "-ijs", "--include-js" },
            description = "A comma-separate list of patterns of files to white-list for compilation to JavaScript.",
            variableArity = true)
    public ArrayList<String> jsIncludes = DefaultConfig.getJsIncludes();

    @Parameter(names = { "-icss", "--include-css" },
            description = "A comma-separate list of patterns of files to white-list for compilation to CSS.",
            variableArity = true)
    public ArrayList<String> cssIncludes = DefaultConfig.getCssIncludes();

    @Parameter(names = { "-iimage", "--include-image" },
            description = "A comma-separate list of patterns of files to white-list for processing as images.",
            variableArity = true)
    public ArrayList<String> imageIncludes = DefaultConfig.getImageIncludes();
    
    @Parameter(names = { "-ejs", "--exclude-js" },
            description = "A comma-separate list of patterns of files to white-list for compilation to JavaScript.",
            variableArity = true)
    public ArrayList<String> jsExcludes = DefaultConfig.getJsExcludes();

    @Parameter(names = { "-ecss", "--exclude-css" },
            description = "A comma-separate list of patterns of files to white-list for compilation to CSS.",
            variableArity = true)
    public ArrayList<String> cssExcludes = DefaultConfig.getCssExcludes();

    @Parameter(names = { "-eimage", "--exclude-image" },
            description = "A comma-separate list of patterns of files to white-list for processing as images.",
            variableArity = true)
    public ArrayList<String> imageExcludes = DefaultConfig.getImageExcludes();
}
