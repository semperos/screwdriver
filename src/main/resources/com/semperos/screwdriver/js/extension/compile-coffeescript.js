var out = CoffeeScript.compile(__Screwdriver.scriptSource, __Screwdriver.compilerOptions);

var handleOutput = function(output) {
    if (__Screwdriver.compilerOptions.sourceMap) {
        return output.v3SourceMap;
    } else {
        return out;
    }
}

handleOutput(out);