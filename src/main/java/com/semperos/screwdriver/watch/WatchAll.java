package com.semperos.screwdriver.watch;

import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

/**
 * Watch asset files for recompilation/reprocessing
 */
public class WatchAll {
    private static Logger logger = Logger.getLogger(WatchAll.class);

    public static void watch(Config cfg) throws Exception {
        logger.info("Starting asset watcher...");
        long interval = 100;
        PipelineEnvironment pe = new PipelineEnvironment(cfg);
        AssetFileObserver fileObserver = new AssetFileObserver(pe);
        FileAlterationObserver coffeeScriptObserver = fileObserver.observeCoffeeScript();
        FileAlterationObserver lessObserver = fileObserver.observeLess();
        FileAlterationObserver imageObserver = fileObserver.observeImage();
        FileAlterationObserver serverTemplateObserver = fileObserver.observeServerTemplate();
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(coffeeScriptObserver);
        monitor.addObserver(lessObserver);
        monitor.addObserver(imageObserver);
        monitor.addObserver(serverTemplateObserver);
        monitor.start();
    }
}
