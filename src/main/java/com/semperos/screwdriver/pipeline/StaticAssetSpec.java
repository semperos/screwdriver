package com.semperos.screwdriver.pipeline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Sometimes you just need to copy something
 */
public class StaticAssetSpec extends AssetSpec {
    public StaticAssetSpec(File assetPath, File outputPath) {
        this(assetPath, new ArrayList<String>(), outputPath);
    }

    public StaticAssetSpec(File assetPath, List<String> assetExtensions, File outputPath) {
        super(assetPath, assetExtensions, outputPath);
    }
}
