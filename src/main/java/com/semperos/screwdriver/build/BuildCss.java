package com.semperos.screwdriver.build;

import com.semperos.screwdriver.IdentityCompiler;
import com.semperos.screwdriver.js.LessCompiler;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.CssAssetSpec;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

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

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceCode = BuildUtil.readFile(sourceFile);
        if (FilenameUtils.isExtension(sourceFile.toString(), "less")) {
            LessCompiler csc = new LessCompiler();
            return csc.compile(sourceCode, sourceFile);
        } else {
            IdentityCompiler idc = new IdentityCompiler();
            return idc.compile(sourceCode, sourceFile);
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        BuildUtil.writeFile(compile(sourceFile),
                cssAssetSpec.outputFile(sourceFile));
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : cssAssetSpec.findFiles()) {
            build(f);
        }
    }
}
