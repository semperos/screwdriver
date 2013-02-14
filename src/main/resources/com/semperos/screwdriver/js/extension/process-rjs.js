//Create a runner that will run a separate build for each item
//in the configs array. Thanks to @jwhitley for this cleverness
for (var config in __Screwdriver.rjs.moduleConfigs) {
    requirejs.optimize(config, function(x) {
        print("RequireJS Optimizer Run");
        print(x);
        print("RequireJS optimization complete for file: " + config.out)
    })
}
