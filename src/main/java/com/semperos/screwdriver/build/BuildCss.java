package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.IdentityCompiler;
import com.semperos.screwdriver.js.LessCompiler;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.CssAssetSpec;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Basic API for building files that compile to CSS
 */
public class BuildCss {
    private static Logger logger = Logger.getLogger(BuildCss.class);
    private CssAssetSpec cssAssetSpec;

    public BuildCss(CssAssetSpec cssAssetSpec) {
        this.cssAssetSpec = cssAssetSpec;
    }

    public String compile(File sourceFile) throws IOException, RhinoEvaluatorException {
        String sourceCode = FileUtil.readFile(sourceFile);
        if (FilenameUtils.isExtension(sourceFile.toString(), "less")) {
            LessCompiler csc = new LessCompiler();
            return csc.compile(sourceFile);
        } else {
            IdentityCompiler idc = new IdentityCompiler();
            return idc.compile(sourceCode, sourceFile);
        }
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        logger.info("Compiling file " + sourceFile.toString() + " to CSS.");
        FileUtil.writeFile(compile(sourceFile),
                cssAssetSpec.outputFile(sourceFile));
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : cssAssetSpec.findFiles()) {
            build(f);
        }
    }

    public void delete(File sourceFile) {
        FileUtils.delete(cssAssetSpec.outputFile(sourceFile));
    }
}
