package com.semperos.screwdriver.pipeline;

import com.semperos.screwdriver.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Test asset specifications
 */
public class AssetSpecTest {
    AssetSpec jsSpec;
    @Before
    public void setUp() throws Exception {
        PipelineEnvironment pe = TestUtil.newPipelineEnvironment();
        jsSpec = pe.getJsAssetSpec();
    }

    @Test
    public void testGetAssetPaths() throws Exception {
        File path = jsSpec.getAssetPath();
        assertTrue(path.exists());
    }

    @Test
    public void testGetAssetExtensions() throws Exception {
        List<String> extensions = jsSpec.getAssetExtensions();
        assertTrue(extensions.size() != 0);
    }

    @Test
    public void testGetFiles() throws Exception {
        List<File> files = jsSpec.findFiles();
        for (File f : files) {
            assertTrue(f.exists());
        }
    }
}
