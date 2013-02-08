package com.semperos.screwdriver;

import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/8/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainIntegrationTest {
    @After
    public void cleanupTestOutput() throws Exception {
        FileUtils.deleteDirectory(new File(TestUtil.testOutputDirectory(), "javascripts"));
        FileUtils.deleteDirectory(new File(TestUtil.testOutputDirectory(), "stylesheets"));
        FileUtils.deleteDirectory(new File(TestUtil.testOutputDirectory(), "images"));
    }

    @Test
    public void testMain() throws Exception {
        String[] args = { "-a", "src/test/resources/com/semperos/screwdriver/sample/assets", "-o", "src/test/resources/com/semperos/screwdriver/sample/output" };
        Main.main(args);
        PipelineEnvironment pe = TestUtil.testPipelineEnvironment();
        ArrayList<File> jsFiles = pe.getJsAssetSpec().getFiles();
        ArrayList<File> cssFiles = pe.getCssAssetSpec().getFiles();
        assertTrue(jsFiles.size() >= 2);
        assertTrue(cssFiles.size() >= 2);
        for (File f : jsFiles) {
            assertTrue(f.exists());
        }
        for (File f : cssFiles) {
            assertTrue(f.exists());
        }
    }

}
