package com.semperos.screwdriver.watch;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Filesystem watcher for client-side assets
 */
public class AssetFileObserver {
    private static Logger logger = Logger.getLogger(AssetFileObserver.class);
    public static FileAlterationObserver observeCoffeeScript(File directory) {
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files       = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".coffee"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        FileAlterationObserver observer = new FileAlterationObserver(directory, filter);
        observer.addListener(new CompileCoffeeScriptListener());
        return observer;
    }

    public static void main(String[] args) throws Exception {
        logger.info("Starting CoffeeScript watcher...");
        long interval = 100;
        FileAlterationObserver coffeeScriptObserver = observeCoffeeScript(new File("."));
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(coffeeScriptObserver);
        monitor.start();
    }
}
