package com.semperos.screwdriver.pipeline;

import com.semperos.screwdriver.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/7/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssetSpecTest {
    AssetSpec jsSpec;
    @Before
    public void setUp() throws Exception {
        PipelineEnvironment pe = TestUtil.testPipelineEnvironment();
        jsSpec = pe.getJsAssetSpec();
    }

    @Test
    public void testGetAssetPaths() throws Exception {
        File path = jsSpec.getAssetPath();
        assertTrue(path.exists());
    }

    @Test
    public void testGetAssetExtensions() throws Exception {
        ArrayList<String> extensions = jsSpec.getAssetExtensions();
        assertTrue(extensions.size() != 0);
    }

    @Test
    public void testGetFiles() throws Exception {
        ArrayList<File> files = jsSpec.getFiles();
        for (File f : files) {
            assertTrue(f.exists());
        }
    }
}
