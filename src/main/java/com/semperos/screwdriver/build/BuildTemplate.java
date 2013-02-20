package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.IdentityCompiler;
import com.semperos.screwdriver.js.JadeCompiler;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.ServerTemplateAssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build "server side" templates
 */
public class BuildTemplate {
    private static Logger logger = Logger.getLogger(BuildTemplate.class);
    private ServerTemplateAssetSpec serverTemplateAssetSpec;
    private JadeCompiler jadeCompiler;

    public BuildTemplate(ServerTemplateAssetSpec serverTemplateAssetSpec) {
        this.serverTemplateAssetSpec = serverTemplateAssetSpec;
        jadeCompiler = new JadeCompiler();
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceCode = FileUtil.readFile(sourceFile);
        if (FilenameUtils.isExtension(sourceFile.toString(), "jade")) {
            jadeCompiler.setCompilerLocals(serverTemplateAssetSpec.getAssetLocals());
            return jadeCompiler.compile(sourceFile);
        } else {
            IdentityCompiler idc = new IdentityCompiler();
            return idc.compile(sourceCode, sourceFile);
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        File outputFile = serverTemplateAssetSpec.outputFile(sourceFile);
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            logger.info("Compiling template file " + sourceFile.toString() + " to HTML.");
            FileUtil.writeFile(compile(sourceFile),
                    outputFile);
        }
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : serverTemplateAssetSpec.findFiles()) {
            build(f);
        }
    }

    public void delete(File sourceFile) {
        serverTemplateAssetSpec.outputFile(sourceFile).delete();
    }
}
