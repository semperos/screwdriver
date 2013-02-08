package com.semperos.screwdriver.build;

import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;

import java.io.IOException;

/**
 * Run all assets through pipeline
 */
public class BuildAll {
    public static void build(PipelineEnvironment pe) throws IOException, RhinoEvaluatorException {
        // Read config for what gets built
        // Build it
        JsAssetSpec jsAsset = pe.getJsAssetSpec();

        BuildJs js = new BuildJs(jsAsset);
        js.buildAll();
    }
}
