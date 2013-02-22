package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.pipeline.*;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

/**
 * Filesystem watcher for client-side assets
 */
public class AssetFileObserver {
    private static Logger logger = Logger.getLogger(AssetFileObserver.class);
    private PipelineEnvironment pe;

    public AssetFileObserver(PipelineEnvironment pe) {
        this.pe = pe;
    }

    public FileAlterationObserver observeJs() {
        JsAssetSpec spec = pe.getJsAssetSpec();
        FileAlterationObserver observer = new FileAlterationObserver(spec.getAssetPath(), spec.activeAssetFileFilter());
        observer.addListener(new BuildJsListener(pe.getJsAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeCss() {
        CssAssetSpec spec = pe.getCssAssetSpec();
        FileAlterationObserver observer = new FileAlterationObserver(spec.getAssetPath(), spec.activeAssetFileFilter());
        observer.addListener(new BuildCssListener(pe.getCssAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeImage() {
        ImageAssetSpec spec = pe.getImageAssetSpec();
        FileAlterationObserver observer = new FileAlterationObserver(spec.getAssetPath(), spec.activeAssetFileFilter());
        observer.addListener(new BuildImageListener(pe.getImageAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeServerTemplate() {
        ServerTemplateAssetSpec spec = pe.getServerTemplateAssetSpec();
        FileAlterationObserver observer = new FileAlterationObserver(spec.getAssetPath(), spec.activeAssetFileFilter());
        observer.addListener(new BuildServerTemplateListener(pe.getServerTemplateAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeTemplate() {
        TemplateAssetSpec spec = pe.getTemplateAssetSpec();
        FileAlterationObserver observer = new FileAlterationObserver(spec.getAssetPath(), spec.activeAssetFileFilter());
        observer.addListener(new BuildTemplateListener(pe.getTemplateAssetSpec()));
        return observer;
    }
}
