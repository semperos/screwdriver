package com.semperos.screwdriver.scripting.js;

import com.semperos.screwdriver.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.Assert.assertEquals;

public class CoffeeScriptEvalIntegrationTest {
    @Before
    public void setup() throws Exception {
        TestUtil.deleteAssetDirectories();
    }

    @After
    public void tearDown() throws Exception {
        TestUtil.deleteAssetDirectories();
    }

    @Test
    public void testEvalFile() throws Exception {
        String expected = "THIS IS COFFEESCRIPT, outputDir is " + TestUtil.baseDirectoryPath();
        File f = new File(TestUtil.baseDirectoryPath(), "screwdriver_build.coffee");
        // Capture stdout and compare
        ByteArrayOutputStream baos = TestUtil.captureStdout();
        CoffeeScriptEval.evalFile(f);
        assertEquals(expected, baos.toString());
        TestUtil.restoreStdout();
    }
}
