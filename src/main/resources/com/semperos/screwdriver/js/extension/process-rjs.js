//Create a runner that will run a separate build for each item
//in the configs array. Thanks to @jwhitley for this cleverness
require(['requirejs'], function(requirejs) {
    for (var key in __Screwdriver) {
        print("Screw KEY: " + key);
    }
    for (var key in requirejs) {
        print("Rjs KEY: " + key);
    }
    for (var i = 0; i < __Screwdriver.rjs.moduleConfigs.length; i++) {
        requirejs.optimize(__Screwdriver.rjs.moduleConfigs[i], function(x) {
            print("RequireJS Optimizer Run");
            print(x);
            print("RequireJS optimization complete for file: " + config.out)
        })
    }
});

