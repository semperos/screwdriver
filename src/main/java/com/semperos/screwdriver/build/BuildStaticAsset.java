package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.pipeline.StaticAssetSpec;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Builds that simply copies static assets
 */
public class BuildStaticAsset {
    private static Logger logger = Logger.getLogger(BuildStaticAsset.class);
    StaticAssetSpec assetSpec;

    public BuildStaticAsset(StaticAssetSpec assetSpec) {
        this.assetSpec = assetSpec;
    }

    // Could have compile/processing function for things like gzipping if appropriate,
    // but not useful now.

    public void build(File sourceFile) throws IOException {
        File outputFile = assetSpec.outputFile(sourceFile);
        String sourceFileName = sourceFile.toString();
        if (FileUtil.needsBuilding(sourceFile, outputFile)) {
            if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
                logger.info("Processing static file " + sourceFileName + ".");
                FileUtil.copyFile(sourceFile, outputFile);
            } else {
                logger.info("Copying file " + sourceFileName + " from the static assets directory.");
                FileUtil.copyFile(sourceFile, outputFile);
            }
        }
    }

    public void buildAll() throws IOException {
        for (File f : assetSpec.findFiles()) {
            build(f);
        }
    }

    public void delete(File sourceFile) {
        assetSpec.outputFile(sourceFile).delete();
    }
}
