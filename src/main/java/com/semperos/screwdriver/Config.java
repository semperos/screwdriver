package com.semperos.screwdriver;

import com.semperos.screwdriver.cli.CommandMain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This is basically a bag of config, currently used solely by the {@link com.semperos.screwdriver.pipeline.PipelineEnvironment}
 * class.
 */
public class Config {
    private Boolean debugMode;
    private File assetDirectory;
    private File outputDirectory;
    private List<String> jsIncludes;
    private List<String> cssIncludes;
    private List<String> imageIncludes;
    private List<String> jsExcludes;
    private List<String> cssExcludes;
    private List<String> imageExcludes;
    private boolean optimizeJs;
    private boolean optimizeCss;
    private boolean optimizeImage;
    private List<String> rjsModules;

    public Config() {
        this.debugMode = DefaultConfig.getDebug();
        this.assetDirectory = new File(DefaultConfig.getAssetDirectory());
        this.outputDirectory = new File(DefaultConfig.getOutputDirectory());
        this.jsIncludes = DefaultConfig.getJsIncludes();
        this.cssIncludes = DefaultConfig.getCssIncludes();
        this.imageIncludes = DefaultConfig.getImageIncludes();
        this.jsExcludes = DefaultConfig.getJsExcludes();
        this.cssExcludes = DefaultConfig.getCssExcludes();
        this.imageExcludes = DefaultConfig.getImageExcludes();
        this.optimizeJs = DefaultConfig.isOptimizeJs();
        this.optimizeCss = DefaultConfig.isOptimizeCss();
        this.optimizeImage = DefaultConfig.isOptimizeImage();
        this.rjsModules = DefaultConfig.getRjsModules();
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
        this.optimizeJs = cm.optimizeJs;
        this.optimizeCss = cm.optimizeCss;
        this.optimizeImage = cm.optimizeImage;
        this.rjsModules = cm.rjsModules;
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

    public List<String> getJsIncludes() {
        return jsIncludes;
    }

    public void setJsIncludes(ArrayList<String> jsIncludes) {
        this.jsIncludes = jsIncludes;
    }

    public List<String> getCssIncludes() {
        return cssIncludes;
    }

    public void setCssIncludes(ArrayList<String> cssIncludes) {
        this.cssIncludes = cssIncludes;
    }

    public List<String> getImageIncludes() {
        return imageIncludes;
    }

    public void setImageIncludes(ArrayList<String> imageIncludes) {
        this.imageIncludes = imageIncludes;
    }

    public List<String> getJsExcludes() {
        return jsExcludes;
    }

    public void setJsExcludes(ArrayList<String> jsExcludes) {
        this.jsExcludes = jsExcludes;
    }

    public List<String> getCssExcludes() {
        return cssExcludes;
    }

    public void setCssExcludes(ArrayList<String> cssExcludes) {
        this.cssExcludes = cssExcludes;
    }

    public List<String> getImageExcludes() {
        return imageExcludes;
    }

    public void setImageExcludes(ArrayList<String> imageExcludes) {
        this.imageExcludes = imageExcludes;
    }

    public boolean isOptimizeJs() {
        return optimizeJs;
    }

    public void setOptimizeJs(boolean optimizeJs) {
        this.optimizeJs = optimizeJs;
    }

    public boolean isOptimizeCss() {
        return optimizeCss;
    }

    public void setOptimizeCss(boolean optimizeCss) {
        this.optimizeCss = optimizeCss;
    }

    public boolean isOptimizeImage() {
        return optimizeImage;
    }

    public void setOptimizeImage(boolean optimizeImage) {
        this.optimizeImage = optimizeImage;
    }

    public List<String> getRjsModules() {
        return rjsModules;
    }

    public void setRjsModules(ArrayList<String> rjsModules) {
        this.rjsModules = rjsModules;
    }
}