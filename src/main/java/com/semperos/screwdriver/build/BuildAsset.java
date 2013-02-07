package com.semperos.screwdriver.build;

import com.semperos.screwdriver.pipeline.AssetSpec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Formalize constructor for asset-specific builders.
 */
public class BuildAsset {
    private AssetSpec assetSpec;

    public AssetSpec getAssetSpec() {
        return assetSpec;
    }

    public BuildAsset(AssetSpec assetSpec) {
        this.assetSpec = assetSpec;
    }

    public String readFile(File sourceFile) throws IOException {
        return readFile(sourceFile, Charset.forName("UTF-8"));
    }

    public String readFile(File sourceFile, Charset charset) throws IOException {
        return FileUtils.readFileToString(sourceFile, charset);
    }

    public void writeFile(String sourceCode, File targetFile) throws IOException {
        writeFile(sourceCode, targetFile, Charset.forName("UTF-8"));
    }

    public void writeFile(String sourceCode, File targetFile, Charset charset) throws IOException {
        FileUtils.writeStringToFile(targetFile, sourceCode, charset);
    }

}

