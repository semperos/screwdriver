package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

/**
 * Specification for client-side (JavaScript) templates
 */
public class TemplateAssetSpec extends AssetSpec {
    public TemplateAssetSpec(File assetPath, List<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }

    @Override
    public String outputFileName(String sourceFileName) {
        if (getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            return FilenameUtils.getBaseName(sourceFileName) + ".js";
        } else {
            return sourceFileName;
        }
    }
}
