package com.semperos.screwdriver.build;

import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.js.RhinoEvaluatorException;
import com.semperos.screwdriver.js.RjsConfigCompiler;
import com.semperos.screwdriver.pipeline.CssAssetSpec;
import com.semperos.screwdriver.pipeline.ImageAssetSpec;
import com.semperos.screwdriver.pipeline.JsAssetSpec;
import com.semperos.screwdriver.pipeline.PipelineEnvironment;

import java.io.IOException;

/**
 * Run all assets through pipeline
 */
public class BuildAll {
    public static void build(Config cfg) throws IOException, RhinoEvaluatorException {
        PipelineEnvironment pe = new PipelineEnvironment(cfg);
        // Read config for what gets built
        // Build it
        JsAssetSpec jsAssetSpec = pe.getJsAssetSpec();
        CssAssetSpec cssAssetSpec = pe.getCssAssetSpec();
        ImageAssetSpec imageAssetSpec = pe.getImageAssetSpec();

        BuildJs js = new BuildJs(jsAssetSpec);
        js.buildAll();

        BuildCss css = new BuildCss(cssAssetSpec);
        css.buildAll();

        BuildImage image = new BuildImage(imageAssetSpec);
        image.buildAll();
    }

    public static void buildAndOptimize(Config cfg) throws IOException, RhinoEvaluatorException {
        build(cfg);
        optimizeJs(cfg);
    }

    private static void optimizeJs(Config cfg) {
        // 1. Boot up r.js via Rhino
        RjsConfigCompiler compiler = new RjsConfigCompiler(cfg);
        // 2. Transform Screwdriver "config" for RequireJS optimizer to individual configs
        //    for each main module to be optimized (like what Mimosa does, except here user has to
        //    specify which are the main modules).
        compiler.processRjsModuleConfigs();
        // 3. Pass each config to requirejs.optimize via Rhino.
    }
}
