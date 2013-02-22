package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Representation of CSS assets in the pipeline
 */
public class CssAssetSpec extends AssetSpec {
    public CssAssetSpec(File assetPath, ArrayList<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }

    public CssAssetSpec(File assetPath, ArrayList<String> assetExtensions, ArrayList<String> fileFilters, File outputPath) {
        super(assetPath, assetExtensions, fileFilters, outputPath);
    }

    @Override
    public String outputFileName(String sourceFileName) {
        if (getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            return FilenameUtils.getBaseName(sourceFileName) + ".css";
        } else {
            return sourceFileName;
        }
    }
}
