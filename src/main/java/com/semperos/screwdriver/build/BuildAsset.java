package com.semperos.screwdriver.build;

import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Build simple assets
 */
public abstract class BuildAsset implements Build {
    private static Logger logger = Logger.getLogger(BuildAsset.class);
    protected AssetSpec assetSpec;

    public BuildAsset(AssetSpec assetSpec) {
        this.assetSpec = assetSpec;
    }

    @Override
    abstract public boolean processFile(File sourceFile, File outputFile) throws IOException;

    @Override
    public boolean build(File sourceFile) throws IOException {
        File outputFile = assetSpec.outputFile(sourceFile);
        boolean processed = false;
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            processed = processFile(sourceFile, outputFile);
        }
        return processed;
    }

    @Override
    public void buildAll() throws IOException {
        for (File f : assetSpec.findFiles()) {
            build(f);
        }
    }

    @Override
    public void delete(File sourceFile) {
        assetSpec.outputFile(sourceFile).delete();
    }
}
