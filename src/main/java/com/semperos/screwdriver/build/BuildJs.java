package com.semperos.screwdriver.build;

import com.semperos.screwdriver.IdentityCompiler;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.js.CoffeeScriptCompiler;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

/**
 * Build assets that compile to JavaScript
 */
public class BuildJs {
    private JsAssetSpec jsAssetSpec;

    public BuildJs(JsAssetSpec jsAssetSpec) {
        this.jsAssetSpec = jsAssetSpec;
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceCode = BuildUtil.readFile(sourceFile);
        if (FilenameUtils.isExtension(sourceFile.toString(), "coffee")) {
            CoffeeScriptCompiler csc = new CoffeeScriptCompiler();
            return csc.compile(sourceCode, sourceFile);
        } else {
            IdentityCompiler idc = new IdentityCompiler();
            return idc.compile(sourceCode, sourceFile);
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        BuildUtil.writeFile(compile(sourceFile),
                jsAssetSpec.outputFile(sourceFile));
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : jsAssetSpec.findFiles()) {
            build(f);
        }
    }
}
