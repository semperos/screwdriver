package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
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
