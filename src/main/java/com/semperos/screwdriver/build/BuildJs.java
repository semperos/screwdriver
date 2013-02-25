package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.CoffeeScriptCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.AssetSpec;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build assets that compile to JavaScript
 */
public class BuildJs extends BuildAssetWithRhino {
    private static Logger logger = Logger.getLogger(BuildJs.class);
    private PipelineEnvironment pe;
    private CoffeeScriptCompiler coffeeScriptCompiler;

    public BuildJs(AssetSpec assetSpec) {
        super(assetSpec);
        this.coffeeScriptCompiler = new CoffeeScriptCompiler();
    }

    public BuildJs(PipelineEnvironment pe, AssetSpec assetSpec) {
        super(assetSpec);
        this.pe = pe;
        this.coffeeScriptCompiler = new CoffeeScriptCompiler();
    }

    private String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (FilenameUtils.isExtension(sourceFileName, "js")) {
            return FileUtil.readFile(sourceFile);
        } else if (FilenameUtils.isExtension(sourceFileName, "coffee")) {
            return this.coffeeScriptCompiler.compile(sourceFile);
        } else {
            throw new RuntimeException("File of type " + sourceFileName + " is not supported by JavaScript compilation at this time.");
        }
    }

    @Override
    public void processFile(File sourceFile, File outputFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            logger.info("Compiling file " + sourceFileName + " to JavaScript.");
            FileUtil.writeFile(compile(sourceFile), outputFile);
        } else if (pe.getTemplateAssetSpec().getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            // JavaScript templates are ignored here so that there aren't miscellaneous *.dust files copied
            // into the output dir as well as their *.js compiled output counterparts. These
            // happen to coexist with regular JavaScript files as a general rule, but are compiled
            // in a separate sweep of the file system because of differing requirements.
            logger.debug("Ignoring JavaScript template " + sourceFileName + " as part of standard JavaScript compilation. Will be compiled in template compilation phase.");
        } else {
            logger.info("Copying file " + sourceFileName + " from the JavaScript directory.");
            FileUtil.copyFile(sourceFile, outputFile);
        }
    }

}
