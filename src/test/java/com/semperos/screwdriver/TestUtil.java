package com.semperos.screwdriver;

import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Data needed across tests
 */
public class TestUtil {
    public static String baseDirectory() {
        return "src/test/resources/com/semperos/screwdriver/sample/";
    }
    public static String assetDirectoryPath() {
        return baseDirectory() + "assets";
    }

    public static String outputDirectoryPath() {
        return baseDirectory() + "output";
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

    public static Config baseConfig() {
        Config cfg = new Config();
        cfg.setAssetDirectory(TestUtil.assetDirectory());
        cfg.setOutputDirectory(outputDirectory());
        HashMap<String,Object> templateLocals = new HashMap<>();
        templateLocals.put("pageTitle", "Testing Jade");
        templateLocals.put("youAreUsingJade", true);
        cfg.setServerTemplateLocals(templateLocals);
        return cfg;
    }

    public static void deleteAssetDirectories() throws IOException {
        FileUtils.deleteDirectory(new File(outputDirectory(), "javascripts"));
        FileUtils.deleteDirectory(new File(outputDirectory(), "stylesheets"));
        FileUtils.deleteDirectory(new File(outputDirectory(), "images"));
        FileUtils.deleteDirectory(new File(outputDirectory(), "built"));
        FileUtils.deleteDirectory(new File(outputDirectory(), "data"));
        (new File(outputDirectory(), "index.html")).delete();
        (new File(outputDirectory(), "clojure-output.txt")).delete();
    }

    public static Collection<File> jsOutputFiles() {
        return FileUtils.listFiles(
                TestUtil.outputDirectory(),
                new RegexFileFilter(".*\\.js"),
                DirectoryFileFilter.DIRECTORY);
    }

    public static Collection<File> cssOutputFiles() {
        return FileUtils.listFiles(
                TestUtil.outputDirectory(),
                new RegexFileFilter(".*\\.css"),
                DirectoryFileFilter.DIRECTORY);
    }

    public static Collection<File> imageOutputFiles() {
        return FileUtils.listFiles(
                TestUtil.outputDirectory(),
                new RegexFileFilter(".*\\.png"),
                DirectoryFileFilter.DIRECTORY);
    }
}
