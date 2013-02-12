package com.semperos.screwdriver.script.kawa;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.cli.CommandBuild;
import com.semperos.screwdriver.cli.CommandDispatcher;
import com.semperos.screwdriver.cli.CommandMain;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * This becomes the entry-point of Screwdriver when built using the appropriate Maven profile.
 */
public class KawaMain {
    public static void main(String[] args) throws Exception {
        CommandMain cm = new CommandMain();
        JCommander jc = new JCommander(cm);
        CommandBuild build = new CommandBuild();
        jc.addCommand("build", build);
        if (args.length == 0) {
            jc.usage();
            System.exit(1);
        }
        jc.parse(args);
        // Short-circuit to show help/usage
        if (cm.help) {
            jc.usage();
            System.exit(0);
        // Use a custom configuration file if supplied
        } else if (cm.config != null) {
            String ext = FilenameUtils.getExtension(cm.config);
            if (ext.equals("scm")) {
                KawaEval.evalFile(new File(cm.config));
            } else {
                throw new RuntimeException("The config file you supplied is not a valid one.");
            }
        // Build using defaults
        } else {
            Config cfg = new Config(cm);
            PipelineEnvironment pe = new PipelineEnvironment(cfg);
            CommandDispatcher.dispatch(jc, pe);
        }
    }
}
