package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
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

    public FileAlterationObserver observeCoffeeScript() {
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files       = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".coffee"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        logger.info("Asset path is: " + pe.getJsAssetSpec().getAssetPath().toString());
        FileAlterationObserver observer = new FileAlterationObserver(pe.getJsAssetSpec().getAssetPath(), filter);
        observer.addListener(new CompileCoffeeScriptListener(pe.getJsAssetSpec()));
        return observer;
    }


}
