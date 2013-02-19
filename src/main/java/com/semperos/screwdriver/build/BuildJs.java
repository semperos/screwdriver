package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.IdentityCompiler;
import com.semperos.screwdriver.js.CoffeeScriptCompiler;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
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
    private JsAssetSpec jsAssetSpec;
    private CoffeeScriptCompiler coffeeScriptCompiler;

    public BuildJs(JsAssetSpec jsAssetSpec) {
        this.jsAssetSpec = jsAssetSpec;
        this.coffeeScriptCompiler = new CoffeeScriptCompiler();
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceCode = FileUtil.readFile(sourceFile);
        if (FilenameUtils.isExtension(sourceFile.toString(), "coffee")) {
            return this.coffeeScriptCompiler.compile(sourceFile);
        } else {
            IdentityCompiler idc = new IdentityCompiler();
            return idc.compile(sourceCode, sourceFile);
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        File outputFile = jsAssetSpec.outputFile(sourceFile);
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            logger.info("Compiling file " + sourceFile.toString() + " to JavaScript.");
            FileUtil.writeFile(compile(sourceFile),
                    jsAssetSpec.outputFile(sourceFile));
        }
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : jsAssetSpec.findFiles()) {
            build(f);
        }
    }

    public void delete(File sourceFile) {
        jsAssetSpec.outputFile(sourceFile).delete();
    }
}
