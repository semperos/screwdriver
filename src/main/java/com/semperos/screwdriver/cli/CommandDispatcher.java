package com.semperos.screwdriver.cli;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.build.BuildAll;
import com.semperos.screwdriver.watch.WatchAll;

/**
 * Dispatch to the appropriate sub-command based on parsed CLI arguments
 */
public class CommandDispatcher {
    public static void dispatch(JCommander jc, Config cfg) throws Exception {
        if (jc.getParsedCommand() != null) {
            if (jc.getParsedCommand().equals("build")) {
                BuildAll.build(cfg);
            } else if (jc.getParsedCommand().equals("watch")) {
                WatchAll.watch(cfg);
            } else {
                throw new RuntimeException("The command " + jc.getParsedCommand() + " is not a supported command.");
            }
        } else if (cfg.isOptimizeJs()) {
            BuildAll.buildAndOptimize(cfg);
        } else {
            BuildAll.build(cfg);
        }
    }
}
