package com.semperos.screwdriver;

import com.semperos.screwdriver.cli.CommandMain;
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
    private List<String> templateIncludes;
    private List<String> templateExcludes;
    private IOFileFilter templateFileFilter;
    private IOFileFilter templateDirFilter;
    private Map<String,Object> templateLocals;
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

    public List<String> getTemplateIncludes() {
        return templateIncludes;
    }

    public void setTemplateIncludes(List<String> templateIncludes) {
        this.templateIncludes = templateIncludes;
    }

    public List<String> getTemplateExcludes() {
        return templateExcludes;
    }

    public void setTemplateExcludes(List<String> templateExcludes) {
        this.templateExcludes = templateExcludes;
    }

    public IOFileFilter getTemplateFileFilter() {
        return templateFileFilter;
    }

    public void setTemplateFileFilter(IOFileFilter templateFileFilter) {
        this.templateFileFilter = templateFileFilter;
    }

    public IOFileFilter getTemplateDirFilter() {
        return templateDirFilter;
    }

    public void setTemplateDirFilter(IOFileFilter templateDirFilter) {
        this.templateDirFilter = templateDirFilter;
    }

    public Map<String, Object> getTemplateLocals() {
        return templateLocals;
    }

    public void setTemplateLocals(Map<String, Object> templateLocals) {
        this.templateLocals = templateLocals;
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