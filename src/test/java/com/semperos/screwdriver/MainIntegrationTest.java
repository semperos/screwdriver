package com.semperos.screwdriver;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.junit.Test;

import java.io.File;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for application entry-point
 */
public class MainIntegrationTest {
//    @After
    public void cleanupTestOutput() throws Exception {
        TestUtil.deleteAssetDirectories();
    }

//    @Test
    public void testMain() throws Exception {
        String outputPath = "src/test/resources/com/semperos/screwdriver/sample/output";
        String[] args = { "-a", "src/test/resources/com/semperos/screwdriver/sample/assets",
                "-o", outputPath };
        Main.main(args);
        LinkedList<File> jsFiles = (LinkedList<File>) FileUtils.listFiles(
                new File(outputPath),
                new RegexFileFilter(".*\\.js"),
                DirectoryFileFilter.DIRECTORY);
        LinkedList<File> cssFiles = (LinkedList<File>) FileUtils.listFiles(
                new File(outputPath),
                new RegexFileFilter(".*\\.css"),
                DirectoryFileFilter.DIRECTORY);
        LinkedList<File> imageFiles = (LinkedList<File>) FileUtils.listFiles(
                new File(outputPath),
                new RegexFileFilter(".*\\.png"),
                DirectoryFileFilter.DIRECTORY);
        assertEquals(2, jsFiles.size());
        assertEquals(5, cssFiles.size());
        assertEquals(1, imageFiles.size());
    }

    @Test
    public void testMainWithCssIncludes() throws Exception {
        String outputPath = "src/test/resources/com/semperos/screwdriver/sample/output";
        String[] args = { "-a", "src/test/resources/com/semperos/screwdriver/sample/assets",
                "-o", outputPath,
                "-icss", ".*?main\\.less"};
        Main.main(args);
        LinkedList<File> files = (LinkedList<File>) FileUtils.listFiles(new File(outputPath), new RegexFileFilter(".*\\.css"), DirectoryFileFilter.DIRECTORY);
        assertEquals(1, files.size());
        assertEquals("main.css", files.get(0).getName());
    }

}
