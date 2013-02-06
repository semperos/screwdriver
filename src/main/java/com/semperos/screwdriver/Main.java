package com.semperos.screwdriver;

import com.beust.jcommander.JCommander;
import com.semperos.screwdriver.cli.CommandBuild;
import com.semperos.screwdriver.cli.CommandMain;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/6/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        CommandMain cm = new CommandMain();
        JCommander jc = new JCommander(cm);
        CommandBuild build = new CommandBuild();
        jc.addCommand("build", build);
        jc.parse(args);
        PipelineEnvironment pe = new PipelineEnvironment();
        System.out.println(cm.basePath);
    }
}
