/*
 * The Rhino version of LESS provides a bare-bones
 * implementation of the 'require' function.
 *
 */
//less = require('less');

__Screwdriver.result = 'COMPILATION FAILED';

var parser = new less.Parser();
parser.parse(__Screwdriver.scriptSource, function(e, tree) {
    if (e instanceof Object) {
        throw e;
    }
    __Screwdriver.result = tree.toCSS({compress: false});
});

__Screwdriver.result;