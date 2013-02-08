package com.semperos.screwdriver.pipeline;

import com.semperos.screwdriver.TestUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsAssetSpecTest {
    JsAssetSpec jsAssetSpec;

    @Before
    public void setUp() throws Exception {
        PipelineEnvironment pe = TestUtil.testPipelineEnvironment();
        jsAssetSpec = pe.getJsAssetSpec();
    }

    @Test
    public void testOutputFileName() throws Exception {
        assertEquals(jsAssetSpec.outputFileName("foo.coffee"), "foo.js");
        assertEquals(jsAssetSpec.outputFileName("/home/foo/bar/foo.bar.coffee"), "foo.bar.js");
    }

    public void testOutputFile() throws Exception {
    }
}
