package com.semperos.screwdriver.pipeline;

import com.semperos.screwdriver.cli.CommandMain;

import java.io.File;
import java.util.ArrayList;

/**
 * Environment for managing paths to various assets to be processed
 * as part of a build.
 */
public class PipelineEnvironment {
    private JsAssetSpec jsAssetSpec;
    private AssetSpec cssAssetSpec;
    private AssetSpec imageAssetSpec;

    public JsAssetSpec getJsAssetSpec() {
        return jsAssetSpec;
    }

    public void setJsAssetSpec(JsAssetSpec jsAssetSpec) {
        this.jsAssetSpec = jsAssetSpec;
    }

    public AssetSpec getCssAssetSpec() {
        return cssAssetSpec;
    }

    public void setCssAssetSpec(AssetSpec cssAssetSpec) {
        this.cssAssetSpec = cssAssetSpec;
    }

    public AssetSpec getImageAssetSpec() {
        return imageAssetSpec;
    }

    public void setImageAssetSpec(AssetSpec imageAssetSpec) {
        this.imageAssetSpec = imageAssetSpec;
    }

    public PipelineEnvironment() {
        this(new File(new CommandMain().assetDirectory));
    }

    public PipelineEnvironment(File assetDirectory) {
        this(assetDirectory, new File(new CommandMain().outputDirectory));
    }
    /**
     * @todo Unhardcode as needed
     */
    public PipelineEnvironment(File assetDirectory, File outputDirectory) {
        ArrayList<File> jsAssetPaths = new ArrayList<File>();
        jsAssetPaths.add(new File(assetDirectory, "javascripts"));
        File jsOutputPath = new File(outputDirectory, "javascripts");
        ArrayList<String> jsAssetExtensions = new ArrayList<String>();
        jsAssetExtensions.add("js");
        jsAssetExtensions.add("coffee");
        jsAssetSpec = new JsAssetSpec(jsAssetPaths, jsAssetExtensions, jsOutputPath);

        ArrayList<File> cssAssetPaths = new ArrayList<File>();
        cssAssetPaths.add(new File(assetDirectory, "stylesheets"));
        File cssOutputPath = new File(outputDirectory, "stylesheets");
        ArrayList<String> cssAssetExtensions = new ArrayList<String>();
        cssAssetExtensions.add("css");
        cssAssetExtensions.add("less");
        cssAssetExtensions.add("sass");
        cssAssetExtensions.add("styl");
        cssAssetSpec = new AssetSpec(cssAssetPaths, cssAssetExtensions, cssOutputPath);

        ArrayList<File> imageAssetPaths = new ArrayList<File>();
        imageAssetPaths.add(new File(assetDirectory, "images"));
        File imageOutputPath = new File(outputDirectory, "images");
        ArrayList<String> imageAssetExtensions = new ArrayList<String>();
        imageAssetExtensions.add("bmp");
        imageAssetExtensions.add("gif");
        imageAssetExtensions.add("jpg");
        imageAssetExtensions.add("jpeg");
        imageAssetExtensions.add("png");
        imageAssetExtensions.add("svg");
        imageAssetSpec = new AssetSpec(imageAssetPaths, imageAssetExtensions, imageOutputPath);
    }

}
