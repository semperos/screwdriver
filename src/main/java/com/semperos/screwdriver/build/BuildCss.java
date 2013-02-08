package com.semperos.screwdriver.build;

import com.semperos.screwdriver.js.LessCompiler;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.CssAssetSpec;
import com.semperos.screwdriver.pipeline.JsAssetSpec;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildCss {

    private CssAssetSpec cssAssetSpec;

    public BuildCss(CssAssetSpec cssAssetSpec) {
        this.cssAssetSpec = cssAssetSpec;
    }

    public String compile(String sourceCode) throws IOException, RhinoEvaluatorException {
        LessCompiler csc = new LessCompiler();
        return csc.compile(sourceCode);
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        BuildUtil.writeFile(compile(BuildUtil.readFile(sourceFile)),
                cssAssetSpec.outputFile(sourceFile));
    }

    public void build(File sourceFile, Charset charset) throws IOException, RhinoEvaluatorException {
        BuildUtil.writeFile(compile(BuildUtil.readFile(sourceFile, charset)),
                cssAssetSpec.outputFile(sourceFile), charset);
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : cssAssetSpec.getFiles()) {
            build(f);
        }
    }
}
