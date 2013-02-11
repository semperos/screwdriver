package com.semperos.screwdriver.pipeline;

import java.io.File;
import java.util.ArrayList;

/**
 * Representation of image assets in the pipeline
 */
public class ImageAssetSpec extends AssetSpec {
    public ImageAssetSpec(File assetPath, ArrayList<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }
}
