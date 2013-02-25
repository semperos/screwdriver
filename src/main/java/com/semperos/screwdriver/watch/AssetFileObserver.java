package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.pipeline.*;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

/**
 * Filesystem watcher for client-side assets
 */
public class AssetFileObserver {
    private PipelineEnvironment pe;

    public AssetFileObserver(PipelineEnvironment pe) {
        this.pe = pe;
    }

    public FileAlterationObserver setupObserver(AssetSpec assetSpec) {
        return new FileAlterationObserver(assetSpec.getAssetPath(), assetSpec.activeAssetFileFilter());
    }

    public FileAlterationObserver observeJs() {
        FileAlterationObserver observer = setupObserver(pe.getJsAssetSpec());
        observer.addListener(new BuildListener(pe.getJsAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeCss() {
        FileAlterationObserver observer = setupObserver(pe.getCssAssetSpec());
        observer.addListener(new BuildListener(pe.getCssAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeImage() {
        FileAlterationObserver observer = setupObserver(pe.getImageAssetSpec());
        observer.addListener(new BuildListener(pe.getImageAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeServerTemplate() {
        FileAlterationObserver observer = setupObserver(pe.getServerTemplateAssetSpec());
        observer.addListener(new BuildListener(pe.getServerTemplateAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeTemplate() {
        FileAlterationObserver observer = setupObserver(pe.getTemplateAssetSpec());
        observer.addListener(new BuildListener(pe.getTemplateAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeStaticAsset() {
        FileAlterationObserver observer = setupObserver(pe.getStaticAssetSpec());
        observer.addListener(new BuildListener(pe.getStaticAssetSpec()));
        return observer;
    }
}
