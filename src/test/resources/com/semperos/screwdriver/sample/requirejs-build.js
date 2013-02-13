// See also https://github.com/requirejs/example-multipage/blob/master/tools/build.js
{
    mainConfigFile: __Screwdriver.rjs.mainConfigFile,                 // 'output/javascripts/common.js',
    optimize: __Screwdriver.rjs.optimizeStatus,                       // 'none',
    baseUrl: __Screwdriver.rjs.baseUrl,                               // 'output/javascripts', // was full canonical path
    findNestedDependencies: __Screwdriver.rjs.findNestedDependencies, // true,
    include: __Screwdriver.rjs.include,                               // [ 'common' ],
    insertRequire: __Screwdriver.rjs.insertRequire,                   // [ 'common' ],
    wrap: __Screwdriver.rjs.wrap,                                     // true,
//  name: __Screwdriver.rjs.almondPath,                               // 'path/to/almond/relative/to/baseUrl', // add this to use Almond.js as a lightweight AMD module loader
    out: __Screwdriver.rjs.out,                                       // '/home/semperos/dev/tradoc/projects/tbr-ui/target/ui/javascripts/common-built.js',
    logLevel: __Screwdriver.rjs.logLevel,                             // 1,
    originalBaseUrl: __Screwdriver.rjs.originalBaseUrl                // '/home/semperos/dev/tradoc/projects/tbr-ui/target/ui/javascripts'
}
