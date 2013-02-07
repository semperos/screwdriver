package com.semperos.screwdriver.build;

import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.AssetSpec;
import com.semperos.screwdriver.pipeline.AssetType;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Run all assets through pipeline
 */
public class BuildAll {
    public static void build(PipelineEnvironment pe) throws IOException, RhinoEvaluatorException {
        // Read config for what gets built
        // Build it
        AssetSpec jsAssetSpec = pe.getJsAssetSpec();

        BuildJs js = new BuildJs(jsAssetSpec);
        js.buildAll();
    }
}
