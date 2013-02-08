__ScrewdriverGlobal.printKeys = function(obj) {
    var keys = Object.keys(obj);
    for (var i = 0; i < keys.length; i++) {
        print("OBJECT KEY: " + keys[i]);
    }
}

__ScrewdriverGlobal.formatObject = function(obj) {
    var result = '';
    var keys = Object.keys(obj);
    for (var i = 0; i < keys.length; i++) {
        var padding;
        if (keys[i].length >= 8) {
            padding = '\t';
        } else {
            padding = '\t\t';
        }
        result += keys[i] + padding + ': ' + obj[keys[i]] + '\n';
    }
    return result;
}

__ScrewdriverGlobal.printObject = function(obj) {
    print(this.formatObject(obj));
}
