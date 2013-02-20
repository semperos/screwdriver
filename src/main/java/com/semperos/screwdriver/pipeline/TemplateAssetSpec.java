package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

/**
 * Asset specifications for "server-side" templates
 */
public class TemplateAssetSpec extends AssetSpec {
    public TemplateAssetSpec(File assetPath, List<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }

    @Override
    public String outputFileName(String sourceFileName) {
        return FilenameUtils.getBaseName(sourceFileName) + ".html";
    }

}
