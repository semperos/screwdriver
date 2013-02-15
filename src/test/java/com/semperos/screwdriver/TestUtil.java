package com.semperos.screwdriver;

import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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

    public static PipelineEnvironment newPipelineEnvironment() {
        return new PipelineEnvironment(TestUtil.testConfig());
    }

    private static Config testConfig() {
        Config cfg = new Config();
        cfg.setAssetDirectory(TestUtil.testAssetDirectory());
        cfg.setOutputDirectory(testOutputDirectory());
        return cfg;
    }

    public static void deleteAssetDirectories() throws IOException {
        FileUtils.deleteDirectory(new File(testOutputDirectory(), "javascripts"));
        FileUtils.deleteDirectory(new File(testOutputDirectory(), "stylesheets"));
        FileUtils.deleteDirectory(new File(testOutputDirectory(), "images"));
        FileUtils.deleteDirectory(new File(testOutputDirectory(), "built"));
    }
}
