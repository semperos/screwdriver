package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.DustCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.AssetSpec;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build client-side JavaScript templates
 */
public class BuildTemplate extends BuildAssetWithRhino {
    private static Logger logger = Logger.getLogger(BuildTemplate.class);
    private PipelineEnvironment pe;
    private DustCompiler dustCompiler;

    public BuildTemplate(PipelineEnvironment pe, AssetSpec assetSpec) {
        super(assetSpec);
        this.pe = pe;
        dustCompiler = new DustCompiler();
    }

    private String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (FilenameUtils.isExtension(sourceFileName, "dust")) {
            return this.dustCompiler.compile(sourceFile);
        } else {
            throw new RuntimeException("File of type " + sourceFileName + " is not supported by JavaScript compilation at this time.");
        }
    }

    @Override
    public boolean processFile(File sourceFile, File outputFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        boolean processed = false;
        if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            logger.info("Compiling template file " + sourceFileName + " to JavaScript.");
            FileUtil.writeFile(compile(sourceFile), outputFile);
            processed = true;
        } else if (this.pe != null && pe.getJsAssetSpec().getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            // Regular JavaScript files are ignored here, because they are handled as part
            // of the BuildJs workflow.
            logger.trace("Ignoring JavaScript file " + sourceFileName + " as part of standard template compilation. Will be compiled in JavaScript compilation phase.");
        } else {
            logger.info("Copying file " + sourceFileName + " from the templates directory.");
            FileUtil.copyFile(sourceFile, outputFile);
            processed = true;
        }
        return processed;
    }

}
