package com.semperos.screwdriver;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.cli.CommandBuild;
import com.semperos.screwdriver.cli.CommandDispatcher;
import com.semperos.screwdriver.cli.CommandMain;
import com.semperos.screwdriver.cli.CommandWatch;

/**
 * Entry-point to Screwdriver if used as executable JAR.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        CommandMain cm = new CommandMain();
        JCommander jc = new JCommander(cm);
        CommandBuild build = new CommandBuild();
        CommandWatch watch = new CommandWatch();
        jc.addCommand("build", build);
        jc.addCommand("watch", watch);
        jc.parse(args);
        // Short-circuit to show help/usage
        if (cm.help) {
            jc.usage();
            System.exit(0);
        } else {
            Config cfg = new Config(cm);
            CommandDispatcher.dispatch(jc, cfg);
        }
    }
}
