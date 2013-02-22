package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.LessCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.CssAssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Basic API for building files that compile to CSS
 */
public class BuildCss {
    private static Logger logger = Logger.getLogger(BuildCss.class);
    private CssAssetSpec assetSpec;

    public BuildCss(CssAssetSpec assetSpec) {
        this.assetSpec = assetSpec;
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (FilenameUtils.isExtension(sourceFileName, "css")) {
            return FileUtil.readFile(sourceFile);
        } else if (FilenameUtils.isExtension(sourceFileName, "less")) {
            LessCompiler csc = new LessCompiler();
            return csc.compile(sourceFile);
        } else {
            throw new RuntimeException("File of type " + sourceFileName + " is not supported by CSS compilation at this time.");
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        File outputFile = assetSpec.outputFile(sourceFile);
        String sourceFileName = sourceFile.toString();
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
                logger.info("Compiling file " + sourceFileName + " to CSS.");
                FileUtil.writeFile(compile(sourceFile), outputFile);
            } else {
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
