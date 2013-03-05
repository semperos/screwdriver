package com.semperos.screwdriver.build;

import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Shared build functionality for all asset types
 */
public abstract class BuildAssetWithRhino implements Build {
    private static Logger logger = Logger.getLogger(BuildAssetWithRhino.class);
    protected AssetSpec assetSpec;

    public BuildAssetWithRhino(AssetSpec assetSpec) {
        this.assetSpec = assetSpec;
    }

    @Override
    abstract public void processFile(File sourceFile, File outputFile) throws IOException, RhinoEvaluatorException;

    public void build(File sourceFile) throws IOException, RhinoEvaluatorException {
        File outputFile = assetSpec.outputFile(sourceFile);
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            processFile(sourceFile, outputFile);
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
