package com.semperos.screwdriver.pipeline;

import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.cli.CommandMain;

import java.io.File;
import java.util.ArrayList;

/**
 * Environment for managing paths to various assets to be processed
 * as part of a build.
 */
public class PipelineEnvironment {
    private JsAssetSpec jsAssetSpec;
    private CssAssetSpec cssAssetSpec;
    private AssetSpec imageAssetSpec;

    public JsAssetSpec getJsAssetSpec() {
        return jsAssetSpec;
    }

    public void setJsAssetSpec(JsAssetSpec jsAssetSpec) {
        this.jsAssetSpec = jsAssetSpec;
    }

    public CssAssetSpec getCssAssetSpec() {
        return cssAssetSpec;
    }

    public void setCssAssetSpec(CssAssetSpec cssAssetSpec) {
        this.cssAssetSpec = cssAssetSpec;
    }

    public AssetSpec getImageAssetSpec() {
        return imageAssetSpec;
    }

    public void setImageAssetSpec(AssetSpec imageAssetSpec) {
        this.imageAssetSpec = imageAssetSpec;
    }

    /**
     * @todo Unhardcode as needed
     */
    public PipelineEnvironment(Config cfg) { //File assetDirectory, File outputDirectory) {
        ArrayList<File> jsAssetPaths = new ArrayList<File>();
        jsAssetPaths.add(new File(cfg.getAssetDirectory(), "javascripts"));
        File jsOutputPath = new File(cfg.getOutputDirectory(), "javascripts");
        ArrayList<String> jsAssetExtensions = new ArrayList<String>();
        jsAssetExtensions.add("js");
        jsAssetExtensions.add("coffee");
        jsAssetSpec = new JsAssetSpec(jsAssetPaths, jsAssetExtensions, jsOutputPath);

        ArrayList<File> cssAssetPaths = new ArrayList<File>();
        cssAssetPaths.add(new File(cfg.getAssetDirectory(), "stylesheets"));
        File cssOutputPath = new File(cfg.getOutputDirectory(), "stylesheets");
        ArrayList<String> cssAssetExtensions = new ArrayList<String>();
        cssAssetExtensions.add("css");
        cssAssetExtensions.add("less");
        cssAssetExtensions.add("sass");
        cssAssetExtensions.add("styl");
        cssAssetSpec = new CssAssetSpec(cssAssetPaths, cssAssetExtensions, cfg.getCssIncludes(), cssOutputPath);

        ArrayList<File> imageAssetPaths = new ArrayList<File>();
        imageAssetPaths.add(new File(cfg.getAssetDirectory(), "images"));
        File imageOutputPath = new File(cfg.getOutputDirectory(), "images");
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
