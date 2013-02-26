package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.pipeline.AssetSpec;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * Filesystem watcher for client-side assets
 */
public class AssetFileObserver {
    private PipelineEnvironment pe;

    public AssetFileObserver(PipelineEnvironment pe) {
        this.pe = pe;
    }

    /**
     * Watch the appropriate asset directory. Higher up in the workflow,
     * the asset's actual "active" files (those that aren't excluded by
     * any other filtering) are the ones that will have any action
     * taken on them. For example, if certain LESS files have been
     * excluded, the config below will still trigger an event when
     * they're changed, but the methods responsible for actually
     * calling the LESS compiler higher up the stack check to ensure that
     * each file is part of the active files.
     *
     * @param assetSpec
     * @return
     */
    public FileAlterationObserver setupObserver(AssetSpec assetSpec) {
        return new FileAlterationObserver(assetSpec.getAssetPath());
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
