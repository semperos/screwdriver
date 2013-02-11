package com.semperos.screwdriver;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.build.BuildAll;
import com.semperos.screwdriver.cli.CommandBuild;
import com.semperos.screwdriver.cli.CommandMain;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/6/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws IOException, RhinoEvaluatorException {
        CommandMain cm = new CommandMain();
        JCommander jc = new JCommander(cm);
        CommandBuild build = new CommandBuild();
        jc.addCommand("build", build);
        jc.parse(args);
        if (cm.help) {
            jc.usage();
            System.exit(0);
        } else {
            Config cfg = new Config();
            cfg.setAssetDirectory(new File(cm.assetDirectory));
            cfg.setOutputDirectory(new File(cm.outputDirectory));
            cfg.setJsIncludes(cm.jsIncludes);
            cfg.setCssIncludes(cm.cssIncludes);
            cfg.setImageIncludes(cm.imageIncludes);
            PipelineEnvironment pe = new PipelineEnvironment(cfg);
            if (jc.getParsedCommand() != null) {
                if (jc.getParsedCommand().equals("build")) {
                    System.out.println(cfg.getCssIncludes().toString());
                    BuildAll.build(pe);
                }
            } else {
                BuildAll.build(pe);
            }

            System.out.println("See " + cm.outputDirectory);
        }
    }
}
