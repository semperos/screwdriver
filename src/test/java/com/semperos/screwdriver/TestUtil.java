package com.semperos.screwdriver;

import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Data needed across tests
 */
public class TestUtil {
    public static String assetDirectoryPath() {
        return "src/test/resources/com/semperos/screwdriver/sample/assets";
    }

    public static String outputDirectoryPath() {
        return "src/test/resources/com/semperos/screwdriver/sample/output";
    }

    public static File assetDirectory() {
        return new File(System.getProperty("user.dir"),
                /**
                 * @todo Set base path up correctly
                 */
                assetDirectoryPath());
    }

    public static File outputDirectory() {
        return new File(System.getProperty("user.dir"),
                /**
                 * @todo Set base path up correctly
                 */
                outputDirectoryPath());
    }

    public static PipelineEnvironment newPipelineEnvironment() {
        return new PipelineEnvironment(TestUtil.baseConfig());
    }

    private static Config baseConfig() {
        Config cfg = new Config();
        cfg.setAssetDirectory(TestUtil.assetDirectory());
        cfg.setOutputDirectory(outputDirectory());
        return cfg;
    }

    public static void deleteAssetDirectories() throws IOException {
        FileUtils.deleteDirectory(new File(outputDirectory(), "javascripts"));
        FileUtils.deleteDirectory(new File(outputDirectory(), "stylesheets"));
        FileUtils.deleteDirectory(new File(outputDirectory(), "images"));
        FileUtils.deleteDirectory(new File(outputDirectory(), "built"));
    }
}
