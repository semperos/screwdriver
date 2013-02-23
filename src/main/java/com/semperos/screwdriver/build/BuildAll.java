package com.semperos.screwdriver.build;

import com.semperos.screwdriver.Config;
import com.semperos.screwdriver.js.rhino.RhinoEvaluatorException;
import com.semperos.screwdriver.js.RjsConfigCompiler;
import com.semperos.screwdriver.pipeline.*;

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
        TemplateAssetSpec templateAssetSpec = pe.getTemplateAssetSpec();
        ServerTemplateAssetSpec serverTemplateAssetSpec = pe.getServerTemplateAssetSpec();
        StaticAssetSpec staticAssetSpec = pe.getStaticAssetSpec();

        BuildJs js = new BuildJs(pe, jsAssetSpec);
        js.buildAll();

        BuildCss css = new BuildCss(cssAssetSpec);
        css.buildAll();

        BuildImage image = new BuildImage(imageAssetSpec);
        image.buildAll();

        BuildTemplate template = new BuildTemplate(templateAssetSpec);
        template.buildAll();

        BuildServerTemplate serverTemplate = new BuildServerTemplate(serverTemplateAssetSpec);
        serverTemplate.buildAll();

        BuildStaticAsset staticAsset = new BuildStaticAsset(staticAssetSpec);
        staticAsset.buildAll();
    }

    public static void buildAndOptimize(Config cfg) throws IOException, RhinoEvaluatorException {
        build(cfg);
        optimizeJs(cfg);
    }

    private static void optimizeJs(Config cfg) throws IOException, RhinoEvaluatorException {
        RjsConfigCompiler compiler = new RjsConfigCompiler(cfg);
        compiler.compile();
    }
}
