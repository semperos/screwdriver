package com.semperos.screwdriver.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.semperos.screwdriver.DefaultConfig;

import java.util.List;

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
    public boolean debug = DefaultConfig.isDebugMode();

    @Parameter(names = { "-h", "--help" }, help = true)
    public boolean help;

    @Parameter(names = { "-a", "--asset-directory" }, description = "The base path from which to calculate asset paths")
    public String assetDirectory = DefaultConfig.getAssetDirectory();

    @Parameter(names = { "-o", "--output-directory" }, description = "The base path from which to calculate where to write processed assets")
    public String outputDirectory = DefaultConfig.getOutputDirectory();

    @Parameter(names = { "-ijs", "--include-js" },
            description = "A space-separate list of patterns of files to white-list for compilation to JavaScript.",
            variableArity = true)
    public List<String> jsIncludes = DefaultConfig.getJsIncludes();

    @Parameter(names = { "-icss", "--include-css" },
            description = "A space-separate list of patterns of files to white-list for compilation to CSS.",
            variableArity = true)
    public List<String> cssIncludes = DefaultConfig.getCssIncludes();

    @Parameter(names = { "-iimage", "--include-image" },
            description = "A space-separate list of patterns of files to white-list for processing as images.",
            variableArity = true)
    public List<String> imageIncludes = DefaultConfig.getImageIncludes();
    
    @Parameter(names = { "-ejs", "--exclude-js" },
            description = "A space-separate list of patterns of files to white-list for compilation to JavaScript.",
            variableArity = true)
    public List<String> jsExcludes = DefaultConfig.getJsExcludes();

    @Parameter(names = { "-ecss", "--exclude-css" },
            description = "A space-separate list of patterns of files to white-list for compilation to CSS.",
            variableArity = true)
    public List<String> cssExcludes = DefaultConfig.getCssExcludes();

    @Parameter(names = { "-eimage", "--exclude-image" },
            variableArity = true,
            description = "A space-separated list of patterns of files to white-list for processing as images.")
    public List<String> imageExcludes = DefaultConfig.getImageExcludes();

    @Parameter(names = { "-m", "--rjs-modules" },
            variableArity = true,
            description = "A space-separated list of AMD module names to target for optimization")
    public List<String> rjsModules = DefaultConfig.getRjsModules();

    @Parameter(names = { "-ojs", "--optimize-js" }, description = "Enable RequireJS optimizations for JavaScript")
    public boolean optimizeJs = DefaultConfig.isOptimizeJs();

    @Parameter(names = { "-ocss", "--optimize-css" }, description = "Enable RequireJS optimizations for CSS")
    public boolean optimizeCss = DefaultConfig.isOptimizeCss();

    @Parameter(names = { "-oimage", "--optimize-image" }, description = "Enable optimizations for images")
    public boolean optimizeImage = DefaultConfig.isOptimizeImage();
}
