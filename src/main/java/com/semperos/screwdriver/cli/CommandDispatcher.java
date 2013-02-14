package com.semperos.screwdriver.cli;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.build.BuildAll;
import com.semperos.screwdriver.js.RhinoEvaluatorException;

import java.io.IOException;

/**
 * Dispatch to the appropriate sub-command based on parsed CLI arguments
 */
public class CommandDispatcher {
    public static void dispatch(JCommander jc, Config cfg) throws IOException, RhinoEvaluatorException {
        if (jc.getParsedCommand() != null) {
            if (jc.getParsedCommand().equals("build")) {
                BuildAll.build(cfg);
            }
        } else if (cfg.isOptimizeJs()) {
            BuildAll.buildAndOptimize(cfg);
        } else {
            BuildAll.build(cfg);
        }
    }
}
