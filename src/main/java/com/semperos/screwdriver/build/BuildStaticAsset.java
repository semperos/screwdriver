package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Builds that simply copies static assets
 */
public class BuildStaticAsset extends BuildAsset {
    private static Logger logger = Logger.getLogger(BuildStaticAsset.class);

    public BuildStaticAsset(AssetSpec assetSpec) {
        super(assetSpec);
    }

    // Could have compile/processing function for things like gzipping if appropriate,
    // but not useful now.
    @Override
    public boolean processFile(File sourceFile, File outputFile) throws IOException {
        String sourceFileName = sourceFile.toString();
        if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            logger.info("Processing static file " + sourceFileName + ".");
            FileUtil.copyFile(sourceFile, outputFile);
        } else {
            logger.info("Copying file " + sourceFileName + " from the static assets directory.");
            FileUtil.copyFile(sourceFile, outputFile);
        }
        return true;
    }

}
