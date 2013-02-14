{
    mainConfigFile: 'output/javascripts/common.js',
    optimize: 'closure',
    closure: {
        CompilerOptions: {},
        CompilationLevel: 'SIMPLE_OPTIMIZATIONS',
        loggingLevel: 'WARNING'
    },
    baseUrl: 'output/javascripts', // was full canonical path
    findNestedDependencies: true,
    include: __Screwdriver.rjs.include,                               // [ 'common' ],
    insertRequire: __Screwdriver.rjs.insertRequire,                   // [ 'common' ],
    // This is relative to baseUrl
    name: 'vendor/almond-0.2.4',
    wrap: true,
    out: 'output/built/javascripts/common-built.js',  // also needs to be dynamic
    logLevel: 0,
    originalBaseUrl: 'output/javascripts'
}
