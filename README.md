# xBoson javasciprt Extension Module

Demonstrates how to write an extension module for use in JavaScript, resulting in the following:

```js
"use strict";
var hello = require("example/hello");
sys.setRetData(0, hello.world());
```

This script will call the java method `public String world()` defined in `org/xboson/example/Hello.java`


## Compilation

Modify the path of `xbosonWar` in the `build.gradle` file.

`gradle`

Publish `build/libs/*.jar` to the Java classpath, and start (or restart) the xBoson platform software.

When installing using [install.sh](https://github.com/xboson-core/xboson-runtime/blob/main/docs/getting-started/adv_install.md), you can:
```bash
docker cp your_module_file.jar xboson-rt:/app/lib/
docker restart xboson-rt
```

## Service Registration

Note that if any extension in any package throws an exception during loading, all extension packages will fail to load. Please pay attention to the output log `user-js-module-loader`. Related text.

This file: `src/main/resources/META-INF/services/com.xboson.exports.IJavaScriptModuleFactory` Registers all exported JavaScript modules in the current Java project, one module per line.


## Factory

`src/main/java/org/xboson/example/Factory.java`
This file exports the module name using `moduleName()`. Instances can be imported by using `require()` with the same package name in the script. Note that the package name must be a complete match; you cannot export `example` and then import `example/hello`.

`src/main/java/org/xboson/example/Hello.java`
This object is ultimately exported to the JavaScript environment and called by users of the module.


## build.gradle

The correct execution of this script depends on the path to `xBoson.war`. Since the class files in the WAR file are in `WEB-INF`, there will be an extraction process.