package com.semperos.screwdriver.build;

import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.js.coffeescript.CoffeeScriptCompiler;
import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Build assets that compile to JavaScript
 */
public class BuildJs extends BuildAsset {
    private final String JS_EXT = "js";

    public BuildJs(AssetSpec assetSpec) {
        super(assetSpec);
    }

    public String compile(String sourceCode) throws IOException, RhinoEvaluatorException {
        CoffeeScriptCompiler csc = new CoffeeScriptCompiler();
        return csc.compile(sourceCode);
    }

    public String outputFileName(String sourceFileName) {
        return FilenameUtils.getBaseName(sourceFileName) + "." + JS_EXT;
    }

    /**
     * This writes back to the same place the source sits. It needs
     * to incorporate the output directory as stored in the {@link AssetSpec}
     * for this instance.
     *
     * @param sourceFile
     * @return
     */
    public File outputFile(File sourceFile) {
        String file = sourceFile.getAbsolutePath();
        String targetName = outputFileName(file);
        String path = FilenameUtils.getFullPath(file);
        return FileUtils.getFile(path, targetName);
    }

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        writeFile(compile(readFile(sourceFile)), outputFile(sourceFile));
    }

    public void build(File sourceFile, Charset charset) throws IOException, RhinoEvaluatorException {
        writeFile(compile(readFile(sourceFile, charset)), outputFile(sourceFile), charset);
    }

    public void buildAll() throws IOException, RhinoEvaluatorException {
        for (File f : getAssetSpec().getFiles()) {
            build(f);
        }
    }
}
