package com.semperos.screwdriver.scripting.clojure;

import clojure.lang.RT;
import clojure.lang.Var;
import com.semperos.screwdriver.TestUtil;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClojureEvalIntegrationTest {
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
        ClojureEval.evalResource("screwdriver-config");
        File targetFile = new File(TestUtil.outputDirectory(), "clojure-output.txt");
        assertTrue(targetFile.exists());
        assertTrue(FileUtils.sizeOf(targetFile) > 0);
        Var cljOutputDir= RT.var("screwdriver-config", "output-dir");
        String outputDir = (String) cljOutputDir.get();
        assertEquals(TestUtil.outputDirectory().getAbsolutePath(), outputDir);
    }

}
