package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.IdentityCompiler;
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
    private TemplateAssetSpec templateAssetSpec;
    private DustCompiler dustCompiler;

    public BuildTemplate(TemplateAssetSpec templateAssetSpec) {
        this.templateAssetSpec = templateAssetSpec;
        dustCompiler = new DustCompiler();
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceCode = FileUtil.readFile(sourceFile);
        if (FilenameUtils.isExtension(sourceFile.toString(), "dust")) {
            return this.dustCompiler.compile(sourceFile);
        } else {
            IdentityCompiler idc = new IdentityCompiler();
            return idc.compile(sourceCode, sourceFile);
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        File outputFile = templateAssetSpec.outputFile(sourceFile);
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            logger.info("Compiling template in " + sourceFile.toString() + " to JavaScript.");
            FileUtil.writeFile(compile(sourceFile),
                    outputFile);
        }
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : templateAssetSpec.findFiles()) {
            build(f);
        }
    }

    public void delete(File sourceFile) {
        templateAssetSpec.outputFile(sourceFile).delete();
    }
}
