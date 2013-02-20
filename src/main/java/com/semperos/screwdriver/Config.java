package com.semperos.screwdriver;

import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is basically a bag of config, currently used solely by the {@link com.semperos.screwdriver.pipeline.PipelineEnvironment}
 * class.
 */
public class Config {
    private Boolean debugMode;
    private File assetDirectory;
    private File outputDirectory;
    private IOFileFilter jsFileFilter;
    private IOFileFilter jsDirFilter;
    private List<String> jsIncludes;
    private List<String> jsExcludes;
    private IOFileFilter cssFileFilter;
    private IOFileFilter cssDirFilter;
    private List<String> cssIncludes;
    private List<String> cssExcludes;
    private IOFileFilter imageFileFilter;
    private IOFileFilter imageDirFilter;
    private List<String> imageIncludes;
    private List<String> imageExcludes;
    private List<String> serverTemplateIncludes;
    private List<String> serverTemplateExcludes;
    private IOFileFilter serverTemplateFileFilter;
    private IOFileFilter serverTemplateDirFilter;
    private Map<String,Object> serverTemplateLocals;
    private boolean optimizeJs;
    private boolean optimizeCss;
    private boolean optimizeImage;
    private List<String> rjsModules;

    public Config() {
        this.debugMode = DefaultConfig.isDebugMode();
        this.assetDirectory = new File(DefaultConfig.getAssetDirectory());
        this.outputDirectory = new File(DefaultConfig.getOutputDirectory());
        this.jsFileFilter = DefaultConfig.getJsFileFilter();
        this.jsDirFilter = DefaultConfig.getJsDirFilter();
        this.jsIncludes = DefaultConfig.getJsIncludes();
        this.jsExcludes = DefaultConfig.getJsExcludes();
        this.cssFileFilter = DefaultConfig.getCssFileFilter();
        this.cssDirFilter = DefaultConfig.getCssDirFilter();
        this.cssIncludes = DefaultConfig.getCssIncludes();
        this.cssExcludes = DefaultConfig.getCssExcludes();
        this.imageFileFilter = DefaultConfig.getImageFileFilter();
        this.imageDirFilter = DefaultConfig.getImageDirFilter();
        this.imageIncludes = DefaultConfig.getImageIncludes();
        this.imageExcludes = DefaultConfig.getImageExcludes();
        this.serverTemplateIncludes = DefaultConfig.getTemplateIncludes();
        this.serverTemplateExcludes = DefaultConfig.getTemplateExcludes();
        this.serverTemplateFileFilter = DefaultConfig.getTemplateFileFilter();
        this.serverTemplateDirFilter = DefaultConfig.getTemplateDirFilter();
        this.serverTemplateLocals = DefaultConfig.getTemplateLocals();

        this.optimizeJs = DefaultConfig.isOptimizeJs();
        this.optimizeCss = DefaultConfig.isOptimizeCss();
        this.optimizeImage = DefaultConfig.isOptimizeImage();
        this.rjsModules = DefaultConfig.getRjsModules();
    }

    public Boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(Boolean debugMode) {
        this.debugMode = debugMode;
    }

    public IOFileFilter getJsFileFilter() {
        return jsFileFilter;
    }

    public void setJsFileFilter(IOFileFilter jsFileFilter) {
        this.jsFileFilter = jsFileFilter;
    }

    public IOFileFilter getCssFileFilter() {
        return cssFileFilter;
    }

    public void setCssFileFilter(IOFileFilter cssFileFilter) {
        this.cssFileFilter = cssFileFilter;
    }

    public IOFileFilter getImageFileFilter() {
        return imageFileFilter;
    }

    public void setImageFileFilter(IOFileFilter imageFileFilter) {
        this.imageFileFilter = imageFileFilter;
    }

    public IOFileFilter getJsDirFilter() {
        return jsDirFilter;
    }

    public void setJsDirFilter(IOFileFilter jsDirFilter) {
        this.jsDirFilter = jsDirFilter;
    }

    public IOFileFilter getCssDirFilter() {
        return cssDirFilter;
    }

    public void setCssDirFilter(IOFileFilter cssDirFilter) {
        this.cssDirFilter = cssDirFilter;
    }

    public IOFileFilter getImageDirFilter() {
        return imageDirFilter;
    }

    public void setImageDirFilter(IOFileFilter imageDirFilter) {
        this.imageDirFilter = imageDirFilter;
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

    public List<String> getServerTemplateIncludes() {
        return serverTemplateIncludes;
    }

    public void setServerTemplateIncludes(List<String> serverTemplateIncludes) {
        this.serverTemplateIncludes = serverTemplateIncludes;
    }

    public List<String> getServerTemplateExcludes() {
        return serverTemplateExcludes;
    }

    public void setServerTemplateExcludes(List<String> serverTemplateExcludes) {
        this.serverTemplateExcludes = serverTemplateExcludes;
    }

    public IOFileFilter getServerTemplateFileFilter() {
        return serverTemplateFileFilter;
    }

    public void setServerTemplateFileFilter(IOFileFilter serverTemplateFileFilter) {
        this.serverTemplateFileFilter = serverTemplateFileFilter;
    }

    public IOFileFilter getServerTemplateDirFilter() {
        return serverTemplateDirFilter;
    }

    public void setServerTemplateDirFilter(IOFileFilter serverTemplateDirFilter) {
        this.serverTemplateDirFilter = serverTemplateDirFilter;
    }

    public Map<String, Object> getServerTemplateLocals() {
        return serverTemplateLocals;
    }

    public void setServerTemplateLocals(Map<String, Object> serverTemplateLocals) {
        this.serverTemplateLocals = serverTemplateLocals;
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