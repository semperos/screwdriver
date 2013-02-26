package com.semperos.screwdriver.scripting.clojure;

import com.semperos.screwdriver.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainClojureIntegrationTest {
    @Before
    public void setup() throws Exception {
        TestUtil.deleteAssetDirectories();
    }

    @After
    public void tearDown() throws Exception {
        TestUtil.deleteAssetDirectories();
    }

    @Test
    public void testMainNoArgs() throws Exception {
        String[] args = { };
        MainClojure.main(args);
        TestUtil.testClojureOutput();
    }

    @Test
    public void testMainCustomConfigFile() throws Exception {
        String[] args = { "screwdriver-custom-config.clj" };
        MainClojure.main(args);
        TestUtil.testClojureOutput("clojure-custom-output.txt");
    }
}
