package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Build simple assets
 */
public class BuildAsset implements Build {
    protected AssetSpec assetSpec;

    public BuildAsset(AssetSpec assetSpec) {
        this.assetSpec = assetSpec;
    }

    @Override
    public void processFile(File sourceFile, File outputFile) throws IOException {
        FileUtil.copyFile(sourceFile, outputFile);
    }

    @Override
    public void build(File sourceFile) throws IOException {
        File outputFile = assetSpec.outputFile(sourceFile);
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            processFile(sourceFile, outputFile);
        }
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
