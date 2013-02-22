package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.CoffeeScriptCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build assets that compile to JavaScript
 */
public class BuildJs {
    private static Logger logger = Logger.getLogger(BuildJs.class);
    private PipelineEnvironment pe;
    private JsAssetSpec assetSpec;
    private CoffeeScriptCompiler coffeeScriptCompiler;

    public BuildJs(JsAssetSpec assetSpec) {
        this.assetSpec = assetSpec;
        this.coffeeScriptCompiler = new CoffeeScriptCompiler();
    }

    public BuildJs(PipelineEnvironment pe, JsAssetSpec assetSpec) {
        this.pe = pe;
        this.assetSpec = assetSpec;
        this.coffeeScriptCompiler = new CoffeeScriptCompiler();
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (FilenameUtils.isExtension(sourceFileName, "js")) {
            return FileUtil.readFile(sourceFile);
        } else if (FilenameUtils.isExtension(sourceFileName, "coffee")) {
            return this.coffeeScriptCompiler.compile(sourceFile);
        } else {
            throw new RuntimeException("File of type " + sourceFileName + " is not supported by JavaScript compilation at this time.");
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        File outputFile = assetSpec.outputFile(sourceFile);
        String sourceFileName = sourceFile.toString();
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
                logger.info("Compiling file " + sourceFileName + " to JavaScript.");
                FileUtil.writeFile(compile(sourceFile), outputFile);
            } else if (pe.getTemplateAssetSpec().getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
                // JavaScript templates are ignored here so that there aren't miscellaneous *.dust files copied
                // into the output dir as well as their *.js compiled output counterparts. These
                // happen to coexist with regular JavaScript files as a general rule, but are compiled
                // in a separate sweep of the file system because of differing requirements.
                logger.debug("Ignoring JavaScript template " + sourceFileName + "as part of standard JavaScript compilation. Will be compiled in template compilation phase.");
            } else {
                logger.info("Copying file " + sourceFileName + " from the JavaScript directory.");
                FileUtil.copyFile(sourceFile, outputFile);
            }
        }
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : assetSpec.findFiles()) {
            build(f);
        }
    }

    public void delete(File sourceFile) {
        assetSpec.outputFile(sourceFile).delete();
    }
}
