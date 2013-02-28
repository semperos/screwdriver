package com.semperos.screwdriver.scripting.clojure;

import com.semperos.screwdriver.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        ClojureEval.evalResource("screwdriver_build");
        TestUtil.testClojureOutput();
    }

}
