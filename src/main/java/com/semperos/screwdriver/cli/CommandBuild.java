package com.semperos.screwdriver.cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/6/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Parameters(commandDescription = "Build all assets")
public class CommandBuild {

    @Parameter(names = { "-o", "--optimize" })
    public boolean optimize = false;
}
