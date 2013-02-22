package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

/**
 * Asset specifications for "server-side" templates
 */
public class ServerTemplateAssetSpec extends AssetSpec {
    public ServerTemplateAssetSpec(File assetPath, List<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }

    @Override
    public String outputFileName(String sourceFileName) {
        if (getAssetExtensions().contains(FilenameUtils.getExtension(sourceFileName))) {
            return FilenameUtils.getBaseName(sourceFileName) + ".html";
        } else {
            return sourceFileName;
        }
    }

}
