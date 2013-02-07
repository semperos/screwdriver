package com.semperos.screwdriver.build;

import com.semperos.screwdriver.TestUtil;
import com.semperos.screwdriver.pipeline.AssetType;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;


/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildJsTest {
    private BuildJs b;

    @Before
    public void setUp() {
        PipelineEnvironment pe = new PipelineEnvironment(TestUtil.testAssetDirectory(), TestUtil.testOutputDirectory());
        b = new BuildJs(pe.getJsAssetSpec());
    }

    public void testReadFile() throws Exception {

    }

    public void testCompile() throws Exception {

    }

    @Test
    public void testOutputFileName() throws Exception {
        assertEquals(b.outputFileName("foo.coffee"), "foo.js");
        assertEquals(b.outputFileName("/home/foo/bar/foo.bar.coffee"), "foo.bar.js");
    }

    public void testOutputFile() throws Exception {
    }
}
