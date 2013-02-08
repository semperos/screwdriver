package com.semperos.screwdriver.build;

import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.js.CoffeeScriptCompiler;
import com.semperos.screwdriver.pipeline.JsAssetSpec;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Build assets that compile to JavaScript
 */
public class BuildJs {
    private JsAssetSpec jsAssetSpec;

    public BuildJs(JsAssetSpec jsAssetSpec) {
        this.jsAssetSpec = jsAssetSpec;
    }

    public String compile(String sourceCode) throws IOException, RhinoEvaluatorException {
        CoffeeScriptCompiler csc = new CoffeeScriptCompiler();
        return csc.compile(sourceCode);
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        BuildUtil.writeFile(compile(BuildUtil.readFile(sourceFile)),
                jsAssetSpec.outputFile(sourceFile));
    }

    public void build(File sourceFile, Charset charset) throws IOException, RhinoEvaluatorException {
        BuildUtil.writeFile(compile(BuildUtil.readFile(sourceFile, charset)),
                jsAssetSpec.outputFile(sourceFile), charset);
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : jsAssetSpec.getFiles()) {
            build(f);
        }
    }
}
