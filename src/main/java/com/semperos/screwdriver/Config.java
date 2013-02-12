package com.semperos.screwdriver;

import com.semperos.screwdriver.cli.CommandMain;

import java.io.File;
import java.util.ArrayList;

/**
 * This is basically a bag of config, currently used solely by the {@link com.semperos.screwdriver.pipeline.PipelineEnvironment}
 * class.
 */
public class Config {
    private Boolean debugMode;
    private File assetDirectory;
    private File outputDirectory;
    private ArrayList<String> jsIncludes;
    private ArrayList<String> cssIncludes;
    private ArrayList<String> imageIncludes;
    private ArrayList<String> jsExcludes;
    private ArrayList<String> cssExcludes;
    private ArrayList<String> imageExcludes;

    public Config() {
    }

    public Config(CommandMain cm) {
        this.debugMode = cm.debug;
        this.assetDirectory = (new File(cm.assetDirectory));
        this.outputDirectory = (new File(cm.outputDirectory));
        this.jsIncludes = cm.jsIncludes;
        this.cssIncludes = cm.cssIncludes;
        this.imageIncludes = cm.imageIncludes;
        this.jsExcludes = cm.jsExcludes;
        this.cssExcludes = cm.cssExcludes;
        this.imageExcludes = cm.imageExcludes;
    }

    public File getAssetDirectory() {
        return assetDirectory;
    }

    public void setAssetDirectory(File assetDirectory) {
        this.assetDirectory = assetDirectory;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public ArrayList<String> getJsIncludes() {
        return jsIncludes;
    }

    public void setJsIncludes(ArrayList<String> jsIncludes) {
        this.jsIncludes = jsIncludes;
    }

    public ArrayList<String> getCssIncludes() {
        return cssIncludes;
    }

    public void setCssIncludes(ArrayList<String> cssIncludes) {
        this.cssIncludes = cssIncludes;
    }

    public ArrayList<String> getImageIncludes() {
        return imageIncludes;
    }

    public void setImageIncludes(ArrayList<String> imageIncludes) {
        this.imageIncludes = imageIncludes;
    }

    public ArrayList<String> getJsExcludes() {
        return jsExcludes;
    }

    public void setJsExcludes(ArrayList<String> jsExcludes) {
        this.jsExcludes = jsExcludes;
    }

    public ArrayList<String> getCssExcludes() {
        return cssExcludes;
    }

    public void setCssExcludes(ArrayList<String> cssExcludes) {
        this.cssExcludes = cssExcludes;
    }

    public ArrayList<String> getImageExcludes() {
        return imageExcludes;
    }

    public void setImageExcludes(ArrayList<String> imageExcludes) {
        this.imageExcludes = imageExcludes;
    }

}