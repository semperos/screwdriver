package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.build.*;
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
        AssetSpec spec = pe.getJsAssetSpec();
        FileAlterationObserver observer = setupObserver(spec);
        BuildJs buildJs = new BuildJs(spec);
        observer.addListener(new BuildListener(spec, buildJs));
        return observer;
    }

    public FileAlterationObserver observeCss() {
        AssetSpec spec = pe.getCssAssetSpec();
        FileAlterationObserver observer = setupObserver(spec);
        BuildCss buildCss = new BuildCss(spec);
        observer.addListener(new BuildListener(spec, buildCss));
        return observer;
    }

    public FileAlterationObserver observeImage() {
        AssetSpec spec = pe.getImageAssetSpec();
        FileAlterationObserver observer = setupObserver(spec);
        BuildImage buildImage = new BuildImage(spec);
        observer.addListener(new BuildListener(spec, buildImage));
        return observer;
    }

    public FileAlterationObserver observeServerTemplate() {
        AssetSpec spec = pe.getServerTemplateAssetSpec();
        FileAlterationObserver observer = setupObserver(spec);
        BuildServerTemplate buildServerTemplate = new BuildServerTemplate(spec);
        observer.addListener(new BuildListener(spec, buildServerTemplate));
        return observer;
    }

    public FileAlterationObserver observeTemplate() {
        AssetSpec spec = pe.getTemplateAssetSpec();
        FileAlterationObserver observer = setupObserver(spec);
        BuildTemplate buildTemplate = new BuildTemplate(spec);
        observer.addListener(new BuildListener(spec, buildTemplate));
        return observer;
    }

    public FileAlterationObserver observeStaticAsset() {
        AssetSpec spec = pe.getStaticAssetSpec();
        FileAlterationObserver observer = setupObserver(spec);
        BuildStaticAsset buildStaticAsset = new BuildStaticAsset(spec);
        observer.addListener(new BuildListener(spec, buildStaticAsset));
        return observer;
    }
}
