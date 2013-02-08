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
    public JsAssetSpec(ArrayList<File> assetPaths, ArrayList<String> assetExtensions, File outputPath) {
        super(assetPaths, assetExtensions, outputPath);
    }

    public String outputFileName(String sourceFileName) {
        return FilenameUtils.getBaseName(sourceFileName) + ".js";
    }

    public File outputFile(File sourceFile) {
        String file = sourceFile.getAbsolutePath();
        String targetName = outputFileName(file);
        String path = getOutputPath().getAbsolutePath();
        return FileUtils.getFile(path, targetName);
    }
}
