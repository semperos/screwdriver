package com.semperos.screwdriver.pipeline;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Environment for managing paths to various assets to be processed
 * as part of a build.
 */
public class PipelineEnvironment {
    private File basePath;
    private HashMap<AssetType, ArrayList<String>> assetExtensions;
    private HashMap<AssetType, ArrayList<File>> assetPaths;

    public File getBasePath() {
        return this.basePath;
    }

    public void setBasePath(File basePath) {
        this.basePath = basePath;
    }

    public PipelineEnvironment() {
        this(new File(System.getProperty("user.dir")));
    }
    /**
     * @todo Unhardcode as needed
     */
    public PipelineEnvironment(File basePath) {
        this.basePath = basePath;
        ArrayList<File> jsPaths = new ArrayList<File>();
        jsPaths.add(new File(basePath, "assets/javascripts"));
        ArrayList<File> cssPaths = new ArrayList<File>();
        cssPaths.add(new File(basePath, "assets/stylesheets"));
        ArrayList<File> imagePaths = new ArrayList<File>();
        imagePaths.add(new File(basePath, "assets/images"));
        assetPaths = new HashMap<AssetType, ArrayList<File>>();
        assetPaths.put(AssetType.JAVASCRIPT, jsPaths);
        assetPaths.put(AssetType.CSS, cssPaths);
        assetPaths.put(AssetType.IMAGE, imagePaths);

        ArrayList<String> jsExtensions = new ArrayList<String>();
        jsExtensions.add("js");
        jsExtensions.add("coffee");
        ArrayList<String> cssExtensions = new ArrayList<String>();
        cssExtensions.add("css");
        cssExtensions.add("less");
        cssExtensions.add("sass");
        cssExtensions.add("styl");
        ArrayList<String> imageExtensions = new ArrayList<String>();
        imageExtensions.add("bmp");
        imageExtensions.add("gif");
        imageExtensions.add("jpg");
        imageExtensions.add("jpeg");
        imageExtensions.add("png");
        imageExtensions.add("svg");
        assetExtensions = new HashMap<AssetType, ArrayList<String>>();
        assetExtensions.put(AssetType.JAVASCRIPT, jsExtensions);
        assetExtensions.put(AssetType.CSS, cssExtensions);
        assetExtensions.put(AssetType.IMAGE, imageExtensions);
    }

    public HashMap<AssetType, ArrayList<File>> getAssetPaths() {
        return assetPaths;
    }


    public ArrayList<File> getAssetPaths(AssetType type) {
        return assetPaths.get(type);
    }

    public HashMap<AssetType, ArrayList<String>> getAssetExtensions() {
        return assetExtensions;
    }

    public ArrayList<String> getAssetExtensions(AssetType type) {
        return assetExtensions.get(type);
    }

    /**
     * Retrieve {@code File} objects for every file of a particular {@link AssetType}.
     *
     * @param type The type of assets to retrieve
     * @return List of all assets of this type currently in filesystem
     */
    public ArrayList<File> getAllAssets(AssetType type) {
        ArrayList<File> assets = new ArrayList<File>();
        ArrayList<String> extensions = getAssetExtensions(type);
        for (String ext : extensions) {
            RegexFileFilter fileFilter = new RegexFileFilter(".*?\\." + ext);
            ArrayList<File> paths = getAssetPaths(type);
            for (File path : paths) {
                assets.addAll(FileUtils.listFiles(
                        path,
                        fileFilter,
                        DirectoryFileFilter.DIRECTORY
                ));
            }
        }
        return assets;
    }
}
