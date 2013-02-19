package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Filesystem watcher for client-side assets
 */
public class AssetFileObserver {
    private static Logger logger = Logger.getLogger(AssetFileObserver.class);
    private PipelineEnvironment pe;

    public AssetFileObserver(PipelineEnvironment pe) {
        this.pe = pe;
    }

    /**
     * Setup an {@link IOFileFilter} that navigates recursively through
     * directories, returning files that match {@code extension}
     *
     * @param extension The extension (leading "." optional) to filter for
     * @return An instance of {@link IOFileFilter} to be used for a file system observer
     */
    private IOFileFilter setupFilters(String extension) {
        if (!extension.startsWith(".")) {
            extension = "." + extension;
        }
        IOFileFilter directories = setupDirectoryFilters();
        IOFileFilter files       = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(extension));
        return FileFilterUtils.or(directories, files);
    }

    private IOFileFilter setupDirectoryFilters() {
        return FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
    }

    private IOFileFilter setupFilters(String[] extensions) {
        IOFileFilter[] fileFilters = new IOFileFilter[extensions.length];
        for (int i = 0; i < extensions.length; i++) {
            fileFilters[i] = FileFilterUtils.and(
                    FileFilterUtils.suffixFileFilter(extensions[i]),
                    FileFilterUtils.fileFileFilter());
        }
        IOFileFilter directories = setupDirectoryFilters();
        IOFileFilter files = FileFilterUtils.or(fileFilters);
        return FileFilterUtils.or(directories, files);
    }

    public FileAlterationObserver observeCoffeeScript() {
        IOFileFilter filter = setupFilters("coffee");
        FileAlterationObserver observer = new FileAlterationObserver(pe.getJsAssetSpec().getAssetPath(), filter);
        observer.addListener(new CompileToJsListener(pe.getJsAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeLess() {
        IOFileFilter filter = setupFilters("less");
        FileAlterationObserver observer = new FileAlterationObserver(pe.getCssAssetSpec().getAssetPath(), filter);
        observer.addListener(new CompileToCssListener(pe.getCssAssetSpec()));
        return observer;
    }

    public FileAlterationObserver observeImage() {
        List<String> assetExtensions = pe.getImageAssetSpec().getAssetExtensions();
        String[] extensions = new String[assetExtensions.size()];
        for (int i = 0; i < extensions.length; i++) {
            extensions[i] = assetExtensions.get(i);
        }
        IOFileFilter filter = setupFilters(extensions);
        FileAlterationObserver observer = new FileAlterationObserver(pe.getImageAssetSpec().getAssetPath(), filter);
        observer.addListener(new ProcessAsImageListener(pe.getImageAssetSpec()));
        return observer;
    }
}
