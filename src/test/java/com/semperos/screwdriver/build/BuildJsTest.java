package com.semperos.screwdriver.build;

import com.semperos.screwdriver.TestUtil;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;
import org.junit.Before;


/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildJsTest {
    private JsAssetSpec jsAssetSpec;
    private BuildJs build;

    @Before
    public void setUp() {
        PipelineEnvironment pe = TestUtil.testPipelineEnvironment();
        jsAssetSpec = pe.getJsAssetSpec();
        build = new BuildJs(jsAssetSpec);
    }

    public void testReadFile() throws Exception {

    }

    public void testCompile() throws Exception {

    }

}
