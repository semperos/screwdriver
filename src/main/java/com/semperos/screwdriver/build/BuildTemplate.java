package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.DustCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build client-side JavaScript templates
 */
public class BuildTemplate extends BuildAssetWithRhino {
    private static Logger logger = Logger.getLogger(BuildTemplate.class);
    private DustCompiler dustCompiler;

    public BuildTemplate(AssetSpec assetSpec) {
        super(assetSpec);
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
    public void processFile(File sourceFile, File outputFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            logger.info("Compiling template file " + sourceFileName + " to JavaScript.");
            FileUtil.writeFile(compile(sourceFile), outputFile);
        } else {
            logger.info("Copying file " + sourceFileName + " from the templates directory.");
            FileUtil.copyFile(sourceFile, outputFile);
        }
    }

}
