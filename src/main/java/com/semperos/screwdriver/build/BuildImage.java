package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Steps required to "build" an image.
 *
 * For now, images are just copied. However, we could also include tooling
 * for applying automatic optimizations/transformations to images, e.g., to
 * keep them at a certain size or quality level.
 */
public class BuildImage extends BuildAsset {
    private static Logger logger = Logger.getLogger(BuildImage.class);

    public BuildImage(AssetSpec assetSpec) {
        super(assetSpec);
    }

    @Override
    public boolean processFile(File sourceFile, File outputFile) throws IOException {
        String sourceFileName = sourceFile.toString();
        if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            logger.info("Processing image file " + sourceFileName + ".");
            FileUtil.copyFile(sourceFile, outputFile);
        } else {
            logger.info("Copying file " + sourceFileName + " from the images directory.");
            FileUtil.copyFile(sourceFile, outputFile);
        }
        return true;
    }
}
