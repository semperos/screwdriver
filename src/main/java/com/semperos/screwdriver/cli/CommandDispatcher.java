package com.semperos.screwdriver.cli;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.build.BuildAll;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;

import java.io.IOException;

/**
 * Dispatch to the appropriate sub-command based on parsed CLI arguments
 */
public class CommandDispatcher {
    public static void dispatch(JCommander jc, PipelineEnvironment pe) throws IOException, RhinoEvaluatorException {
        if (jc.getParsedCommand() != null) {
            if (jc.getParsedCommand().equals("build")) {
                BuildAll.build(pe);
            }
        } else {
            BuildAll.build(pe);
        }
    }
}
