package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.DustCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.TemplateAssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build client-side JavaScript templates
 */
public class BuildTemplate {
    private static Logger logger = Logger.getLogger(BuildTemplate.class);
    private TemplateAssetSpec assetSpec;
    private DustCompiler dustCompiler;

    public BuildTemplate(TemplateAssetSpec assetSpec) {
        this.assetSpec = assetSpec;
        dustCompiler = new DustCompiler();
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (FilenameUtils.isExtension(sourceFileName, "dust")) {
            return this.dustCompiler.compile(sourceFile);
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
                logger.info("Compiling template file " + sourceFileName + " to JavaScript.");
                FileUtil.writeFile(compile(sourceFile), outputFile);
            } else {
                logger.info("Copying file " + sourceFileName + " from the templates directory.");
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
