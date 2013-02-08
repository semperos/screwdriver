package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/8/13
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class CssAssetSpec extends AssetSpec {
    public CssAssetSpec(ArrayList<File> assetPaths, ArrayList<String> assetExtensions, File outputPath) {
        super(assetPaths, assetExtensions, outputPath);
    }

    public CssAssetSpec(ArrayList<File> assetPaths, ArrayList<String> assetExtensions, ArrayList<String> assetIncludes, File outputPath) {
        super(assetPaths, assetExtensions, assetIncludes, outputPath);
    }

    @Override
    public String outputFileName(String sourceFileName) {
        return FilenameUtils.getBaseName(sourceFileName) + ".css";
    }
}
