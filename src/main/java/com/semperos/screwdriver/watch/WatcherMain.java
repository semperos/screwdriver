package com.semperos.screwdriver.watch;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.cli.CommandMain;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

/**
 * Entry-point for filesystem watching
 */
public class WatcherMain {
    private static Logger logger = Logger.getLogger(WatcherMain.class);

    public static void main(String[] args) throws Exception {
        CommandMain cm = new CommandMain();
        JCommander jc = new JCommander(cm);
        jc.parse(args);
        // Short-circuit to show help/usage
        if (cm.help) {
            jc.usage();
            System.exit(0);
        } else {
            Config cfg = new Config(cm);
            logger.info("Starting asset watcher...");
            long interval = 100;
            PipelineEnvironment pe = new PipelineEnvironment(cfg);
            AssetFileObserver fileObserver = new AssetFileObserver(pe);
            FileAlterationObserver coffeeScriptObserver = fileObserver.observeCoffeeScript();
            FileAlterationObserver lessObserver = fileObserver.observeLess();
            FileAlterationObserver imageObserver = fileObserver.observeImage();
            FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
            monitor.addObserver(coffeeScriptObserver);
            monitor.addObserver(lessObserver);
            monitor.addObserver(imageObserver);
            monitor.start();
        }
    }
}
