package com.semperos.screwdriver;

import com.semperos.screwdriver.pipeline.PipelineEnvironment;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestUtil {
    public static File testAssetDirectory() {
        return new File(System.getProperty("user.dir"),
                /**
                 * @todo Set base path up correctly
                 */
                "src/test/resources/com/semperos/screwdriver/sample/assets");
    }

    public static File testOutputDirectory() {
        return new File(System.getProperty("user.dir"),
                /**
                 * @todo Set base path up correctly
                 */
                "src/test/resources/com/semperos/screwdriver/sample/output");
    }

    public static PipelineEnvironment testPipelineEnvironment() {
        return new PipelineEnvironment(TestUtil.testAssetDirectory(), TestUtil.testOutputDirectory());
    }
}
