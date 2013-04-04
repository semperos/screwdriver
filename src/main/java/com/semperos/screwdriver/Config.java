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
    private boolean jsSourceMapsEnabled;
    private String jsSubDirectoryName;
    private String cssSubDirectoryName;
    private String imageSubDirectoryName;
    private String templateSubDirectoryName;
    private String serverTemplateSubDirectoryName;
    private String staticAssetSubDirectoryName;
    private ArrayList<String> jsExtensions;
    private ArrayList<String> cssExtensions;
    private ArrayList<String> imageExtensions;
    private ArrayList<String> templateExtensions;
    private ArrayList<String> serverTemplateExtensions;
    private ArrayList<String> staticAssetExtensions;
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
    private List<String> serverTemplateIncludes;
    private List<String> serverTemplateExcludes;
    private IOFileFilter serverTemplateFileFilter;
    private IOFileFilter serverTemplateDirFilter;
    private Map<String,Object> serverTemplateLocals;
    private List<String> staticAssetIncludes;
    private List<String> staticAssetExcludes;
    private IOFileFilter staticAssetFileFilter;
    private IOFileFilter staticAssetDirFilter;
    private boolean optimizeJs;
    private boolean optimizeCss;
    private boolean optimizeImage;
    private List<String> rjsModules;
    private String rjsMainConfigFile;
    private String rjsBaseUrl;
    private String rjsOutFormat;

    public Config() {
        this.debugMode = DefaultConfig.isDebugMode();
        this.assetDirectory = new File(DefaultConfig.getAssetDirectory());
        this.outputDirectory = new File(DefaultConfig.getOutputDirectory());
        this.jsSourceMapsEnabled = DefaultConfig.isJsSourceMapsEnabled();
        this.jsSubDirectoryName = DefaultConfig.getJsSubDirectoryName();
        this.cssSubDirectoryName = DefaultConfig.getCssSubDirectoryName();
        this.imageSubDirectoryName = DefaultConfig.getImageSubDirectoryName();
        this.templateSubDirectoryName = DefaultConfig.getTemplateSubDirectoryName();
        this.serverTemplateSubDirectoryName = DefaultConfig.getServerTemplateSubDirectoryName();
        this.staticAssetSubDirectoryName = DefaultConfig.getStaticAssetDirectoryName();
        this.jsExtensions = DefaultConfig.getJsExtensions();
        this.cssExtensions = DefaultConfig.getCssExtensions();
        this.imageExtensions = DefaultConfig.getImageExtensions();
        this.templateExtensions = DefaultConfig.getTemplateExtensions();
        this.serverTemplateExtensions = DefaultConfig.getServerTemplateExtensions();
        this.staticAssetExtensions = DefaultConfig.getStaticAssetExtensions();
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
        this.templateIncludes = DefaultConfig.getTemplateIncludes();
        this.templateExcludes = DefaultConfig.getTemplateExcludes();
        this.templateFileFilter = DefaultConfig.getTemplateFileFilter();
        this.templateDirFilter = DefaultConfig.getTemplateDirFilter();
        this.serverTemplateIncludes = DefaultConfig.getServerTemplateIncludes();
        this.serverTemplateExcludes = DefaultConfig.getServerTemplateExcludes();
        this.serverTemplateFileFilter = DefaultConfig.getServerTemplateFileFilter();
        this.serverTemplateDirFilter = DefaultConfig.getServerTemplateDirFilter();
        this.serverTemplateLocals = DefaultConfig.getServerTemplateLocals();
        this.staticAssetFileFilter = DefaultConfig.getStaticAssetFileFilter();
        this.staticAssetDirFilter = DefaultConfig.getStaticAssetDirFilter();
        this.staticAssetIncludes = DefaultConfig.getStaticAssetIncludes();
        this.staticAssetExcludes = DefaultConfig.getStaticAssetExcludes();
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

    public boolean isJsSourceMapsEnabled() {
        return jsSourceMapsEnabled;
    }

    public void setJsSourceMapsEnabled(boolean jsSourceMapsEnabled) {
        this.jsSourceMapsEnabled = jsSourceMapsEnabled;
    }

    public String getJsSubDirectoryName() {
        return jsSubDirectoryName;
    }

    public void setJsSubDirectoryName(String jsSubDirectoryName) {
        this.jsSubDirectoryName = jsSubDirectoryName;
    }

    public String getCssSubDirectoryName() {
        return cssSubDirectoryName;
    }

    public void setCssSubDirectoryName(String cssSubDirectoryName) {
        this.cssSubDirectoryName = cssSubDirectoryName;
    }

    public String getImageSubDirectoryName() {
        return imageSubDirectoryName;
    }

    public void setImageSubDirectoryName(String imageSubDirectoryName) {
        this.imageSubDirectoryName = imageSubDirectoryName;
    }

    public String getTemplateSubDirectoryName() {
        return templateSubDirectoryName;
    }

    public void setTemplateSubDirectoryName(String templateSubDirectoryName) {
        this.templateSubDirectoryName = templateSubDirectoryName;
    }

    public String getServerTemplateSubDirectoryName() {
        return serverTemplateSubDirectoryName;
    }

    public void setServerTemplateSubDirectoryName(String serverTemplateSubDirectoryName) {
        this.serverTemplateSubDirectoryName = serverTemplateSubDirectoryName;
    }

    public String getStaticAssetSubDirectoryName() {
        return staticAssetSubDirectoryName;
    }

    public void setStaticAssetSubDirectoryName(String staticAssetSubDirectoryName) {
        this.staticAssetSubDirectoryName = staticAssetSubDirectoryName;
    }

    public ArrayList<String> getJsExtensions() {
        return jsExtensions;
    }

    public void setJsExtensions(ArrayList<String> jsExtensions) {
        this.jsExtensions = jsExtensions;
    }

    public ArrayList<String> getCssExtensions() {
        return cssExtensions;
    }

    public void setCssExtensions(ArrayList<String> cssExtensions) {
        this.cssExtensions = cssExtensions;
    }

    public ArrayList<String> getImageExtensions() {
        return imageExtensions;
    }

    public void setImageExtensions(ArrayList<String> imageExtensions) {
        this.imageExtensions = imageExtensions;
    }

    public ArrayList<String> getTemplateExtensions() {
        return templateExtensions;
    }

    public void setTemplateExtensions(ArrayList<String> templateExtensions) {
        this.templateExtensions = templateExtensions;
    }

    public ArrayList<String> getServerTemplateExtensions() {
        return serverTemplateExtensions;
    }

    public void setServerTemplateExtensions(ArrayList<String> serverTemplateExtensions) {
        this.serverTemplateExtensions = serverTemplateExtensions;
    }

    public ArrayList<String> getStaticAssetExtensions() {
        return staticAssetExtensions;
    }

    public void setStaticAssetExtensions(ArrayList<String> staticAssetExtensions) {
        this.staticAssetExtensions = staticAssetExtensions;
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

    public List<String> getStaticAssetIncludes() {
        return staticAssetIncludes;
    }

    public void setStaticAssetIncludes(List<String> staticAssetIncludes) {
        this.staticAssetIncludes = staticAssetIncludes;
    }

    public List<String> getStaticAssetExcludes() {
        return staticAssetExcludes;
    }

    public void setStaticAssetExcludes(List<String> staticAssetExcludes) {
        this.staticAssetExcludes = staticAssetExcludes;
    }

    public IOFileFilter getStaticAssetFileFilter() {
        return staticAssetFileFilter;
    }

    public void setStaticAssetFileFilter(IOFileFilter staticAssetFileFilter) {
        this.staticAssetFileFilter = staticAssetFileFilter;
    }

    public IOFileFilter getStaticAssetDirFilter() {
        return staticAssetDirFilter;
    }

    public void setStaticAssetDirFilter(IOFileFilter staticAssetDirFilter) {
        this.staticAssetDirFilter = staticAssetDirFilter;
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

    public String getRjsMainConfigFile() {
        return rjsMainConfigFile;
    }

    public void setRjsMainConfigFile(String rjsMainConfigFile) {
        this.rjsMainConfigFile = rjsMainConfigFile;
    }

    public String getRjsBaseUrl() {
        return rjsBaseUrl;
    }

    public void setRjsBaseUrl(String rjsBaseUrl) {
        this.rjsBaseUrl = rjsBaseUrl;
    }

    public String getRjsOutFormat() {
        return rjsOutFormat;
    }

    public void setRjsOutFormat(String rjsOutFormat) {
        this.rjsOutFormat = rjsOutFormat;
    }
}