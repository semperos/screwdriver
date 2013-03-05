package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Spec to manage compilation of assets to JS source maps
 */
public class JsSourceMapAssetSpec extends AssetSpec {
    public JsSourceMapAssetSpec(File assetPath, ArrayList<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }

    @Override
    public String outputFileName(String sourceFileName) {
        if (getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            return FilenameUtils.getBaseName(sourceFileName) + ".js.map";
        } else {
            return sourceFileName;
        }
    }
}
