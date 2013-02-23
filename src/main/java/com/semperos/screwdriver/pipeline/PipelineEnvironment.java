package com.semperos.screwdriver.pipeline;

import com.semperos.screwdriver.Config;

import java.io.File;

/**
 * Environment for managing paths to various assets to be processed
 * as part of a build.
 */
public class PipelineEnvironment {
    private JsAssetSpec jsAssetSpec;
    private CssAssetSpec cssAssetSpec;
    private ImageAssetSpec imageAssetSpec;
    private TemplateAssetSpec templateAssetSpec;
    private ServerTemplateAssetSpec serverTemplateAssetSpec;

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

    public ImageAssetSpec getImageAssetSpec() {
        return imageAssetSpec;
    }

    public void setImageAssetSpec(ImageAssetSpec imageAssetSpec) {
        this.imageAssetSpec = imageAssetSpec;
    }

    public TemplateAssetSpec getTemplateAssetSpec() {
        return templateAssetSpec;
    }

    public void setTemplateAssetSpec(TemplateAssetSpec templateAssetSpec) {
        this.templateAssetSpec = templateAssetSpec;
    }

    public ServerTemplateAssetSpec getServerTemplateAssetSpec() {
        return serverTemplateAssetSpec;
    }

    public void setServerTemplateAssetSpec(ServerTemplateAssetSpec serverTemplateAssetSpec) {
        this.serverTemplateAssetSpec = serverTemplateAssetSpec;
    }

    /**
     * @todo Unhardcode as needed
     */
    public PipelineEnvironment(Config cfg) {
        File jsAssetPath = new File(cfg.getAssetDirectory(), cfg.getJsSubDirectoryName());
        File jsOutputPath = new File(cfg.getOutputDirectory(), cfg.getJsSubDirectoryName());
        jsAssetSpec = new JsAssetSpec(jsAssetPath, cfg.getJsExtensions(), jsOutputPath);
        /**
         * @todo The following is repeated throughout this method; refactor
         */
        if (cfg.getJsFileFilter() != null) {
            jsAssetSpec.setAssetFileFilter(cfg.getJsFileFilter());
        }
        if (cfg.getJsDirFilter() != null) {
            jsAssetSpec.setAssetDirFilter(cfg.getJsDirFilter());
        }
        if (cfg.getJsIncludes() != null && cfg.getJsIncludes().size() > 0) {
            jsAssetSpec.setAssetIncludes(cfg.getJsIncludes());
        } else {
            jsAssetSpec.setAssetExcludes(cfg.getJsExcludes());
        }

        File cssAssetPath = new File(cfg.getAssetDirectory(), cfg.getCssSubDirectoryName());
        File cssOutputPath = new File(cfg.getOutputDirectory(), cfg.getCssSubDirectoryName());
        cssAssetSpec = new  CssAssetSpec(cssAssetPath, cfg.getCssExtensions(), cssOutputPath);
        if (cfg.getCssFileFilter() != null) {
            cssAssetSpec.setAssetFileFilter(cfg.getCssFileFilter());
        }
        if (cfg.getCssDirFilter() != null) {
            cssAssetSpec.setAssetDirFilter(cfg.getCssDirFilter());
        }
        if (cfg.getCssIncludes() != null && cfg.getCssIncludes().size() > 0) {
            cssAssetSpec.setAssetIncludes(cfg.getCssIncludes());
        } else {
            cssAssetSpec.setAssetExcludes(cfg.getCssExcludes());
        }


        File imageAssetPath = new File(cfg.getAssetDirectory(), cfg.getImageSubDirectoryName());
        File imageOutputPath = new File(cfg.getOutputDirectory(), cfg.getImageSubDirectoryName());
        imageAssetSpec = new ImageAssetSpec(imageAssetPath, cfg.getImageExtensions(), imageOutputPath);
        if (cfg.getImageFileFilter() != null) {
            imageAssetSpec.setAssetFileFilter(cfg.getImageFileFilter());
        }
        if (cfg.getImageDirFilter() != null) {
            imageAssetSpec.setAssetDirFilter(cfg.getImageDirFilter());
        }
        if (cfg.getImageIncludes() != null && cfg.getImageIncludes().size() > 0) {
            imageAssetSpec.setAssetIncludes(cfg.getImageIncludes());
        } else {
            imageAssetSpec.setAssetExcludes(cfg.getImageExcludes());
        }

        File templateAssetPath = new File(cfg.getAssetDirectory(), cfg.getJsSubDirectoryName());
        File templateOutputPath = new File(cfg.getOutputDirectory(), cfg.getJsSubDirectoryName());
        templateAssetSpec= new TemplateAssetSpec(templateAssetPath, cfg.getTemplateExtensions(), templateOutputPath);
        if (cfg.getTemplateFileFilter() != null) {
            templateAssetSpec.setAssetFileFilter(cfg.getTemplateFileFilter());
        }
        if (cfg.getTemplateDirFilter() != null) {
            templateAssetSpec.setAssetDirFilter(cfg.getTemplateDirFilter());
        }
        if (cfg.getTemplateIncludes() != null && cfg.getTemplateIncludes().size() > 0) {
            templateAssetSpec.setAssetIncludes(cfg.getTemplateIncludes());
        } else {
            templateAssetSpec.setAssetExcludes(cfg.getTemplateExcludes());
        }

        File serverTemplateAssetPath = new File(cfg.getAssetDirectory(), cfg.getServerTemplateSubDirectoryName());
        File serverTemplateOutputPath = cfg.getOutputDirectory();
        serverTemplateAssetSpec = new ServerTemplateAssetSpec(serverTemplateAssetPath,
                cfg.getServerTemplateExtensions(),
                serverTemplateOutputPath);
        serverTemplateAssetSpec.setAssetLocals(cfg.getServerTemplateLocals());
        if (cfg.getServerTemplateFileFilter() != null) {
            serverTemplateAssetSpec.setAssetFileFilter(cfg.getServerTemplateFileFilter());
        }
        if (cfg.getServerTemplateDirFilter() != null) {
            serverTemplateAssetSpec.setAssetDirFilter(cfg.getServerTemplateDirFilter());
        }
        if (cfg.getServerTemplateIncludes() != null && cfg.getServerTemplateIncludes().size() > 0) {
            serverTemplateAssetSpec.setAssetIncludes(cfg.getServerTemplateIncludes());
        } else {
            serverTemplateAssetSpec.setAssetExcludes(cfg.getServerTemplateExcludes());
        }
    }

}
