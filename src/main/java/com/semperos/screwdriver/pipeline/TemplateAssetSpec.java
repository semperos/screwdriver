package com.semperos.screwdriver.pipeline;

import java.io.File;
import java.util.List;

/**
 * Specification for client-side (JavaScript) templates
 */
public class TemplateAssetSpec extends AssetSpec {
    public TemplateAssetSpec(File assetPath, List<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }
}
