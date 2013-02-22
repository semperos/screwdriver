package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.js.JadeCompiler;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.ServerTemplateAssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build "server side" templates
 */
public class BuildServerTemplate {
    private static Logger logger = Logger.getLogger(BuildServerTemplate.class);
    private ServerTemplateAssetSpec assetSpec;
    private JadeCompiler jadeCompiler;

    public BuildServerTemplate(ServerTemplateAssetSpec assetSpec) {
        this.assetSpec = assetSpec;
        jadeCompiler = new JadeCompiler();
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceFileName = sourceFile.toString();
        if (FilenameUtils.isExtension(sourceFileName, "html")) {
            return FileUtil.readFile(sourceFile);
        } else if (FilenameUtils.isExtension(sourceFileName, "jade")) {
            jadeCompiler.setCompilerLocals(assetSpec.getAssetLocals());
            return jadeCompiler.compile(sourceFile);
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
                logger.info("Compiling template file " + sourceFileName + " to HTML.");
                FileUtil.writeFile(compile(sourceFile), outputFile);
            } else {
                logger.info("Copying file " + sourceFileName + " from the server templates directory.");
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
