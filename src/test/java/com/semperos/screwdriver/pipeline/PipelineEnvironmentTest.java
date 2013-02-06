package com.semperos.screwdriver.pipeline;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: semperos
 * Date: 2/6/13
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class PipelineEnvironmentTest {
    private PipelineEnvironment pe;
    @Before
    public void setUp() throws Exception {
        this.pe = new PipelineEnvironment(new File(
                System.getProperty("user.dir"),
                /**
                 * @todo Set base path up correctly
                 */
                "src/test/resources/com/semperos/screwdriver/sample"));
    }

    @Test
    public void testGetAssetPaths() throws Exception {
        HashMap<AssetType, ArrayList<File>> allPaths = pe.getAssetPaths();
        assertTrue(allPaths.size() >= 3);
        for (Map.Entry<AssetType, ArrayList<File>> path : allPaths.entrySet()) {
            for (File f : path.getValue()) {
                assertTrue(f.exists());
            }
        }
    }

    @Test
    public void testGetAssetExtensions() throws Exception {
        HashMap<AssetType, ArrayList<String>> extensions = pe.getAssetExtensions();
        assertTrue(extensions.size() >= 2);
    }

    @Test
    public void testGetAllAssets() throws Exception {
        ArrayList assets = pe.getAllAssets(AssetType.JAVASCRIPT);
        assertTrue(assets.size() >= 2);
    }
}
