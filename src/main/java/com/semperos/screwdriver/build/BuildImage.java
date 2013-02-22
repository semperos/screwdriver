package com.semperos.screwdriver.build;

import com.semperos.screwdriver.FileUtil;
import com.semperos.screwdriver.pipeline.ImageAssetSpec;
import org.apache.commons.io.FileUtils;
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
public class BuildImage {
    private static Logger logger = Logger.getLogger(BuildImage.class);
    ImageAssetSpec assetSpec;
    public BuildImage(ImageAssetSpec assetSpec) {
        this.assetSpec = assetSpec;
    }

    public void compile(File sourceFile) {
        // This is a stand-in for possible future functionality, e.g., auto-optimization
        // of images for the web
    }

    public void build(File sourceFile) throws IOException {
        File outputFile = assetSpec.outputFile(sourceFile);
        String sourceFileName = sourceFile.toString();
        if ((!outputFile.exists()) ||
                (outputFile.exists() && FileUtils.isFileNewer(sourceFile, outputFile))) {
            if (assetSpec.getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
                logger.info("Processing image file " + sourceFileName + ".");
                FileUtil.copyFile(sourceFile, outputFile);
            } else {
                logger.info("Copying file " + sourceFileName + " from the images directory.");
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
