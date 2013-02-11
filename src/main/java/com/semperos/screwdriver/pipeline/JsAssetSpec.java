package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Representation of JavaScript assets in the pipeline.
 */
public class JsAssetSpec extends AssetSpec {
    public JsAssetSpec(File assetPath, ArrayList<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }

    @Override
    public String outputFileName(String sourceFileName) {
        return FilenameUtils.getBaseName(sourceFileName) + ".js";
    }

}
