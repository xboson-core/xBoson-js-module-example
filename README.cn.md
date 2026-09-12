# xBoson js 扩展模块

演示如何编写扩展模块在 javascript 中使用, 最终如下效果:

```js
"use strict";
var hello = require("example/hello");
sys.setRetData(0, hello.world());
```

这个脚本将调用 `org/xboson/example/Hello.java` 中定义的方法 `public String world()`


## 编译

获取 xboson.jar 文件:
```bash
docker run -i --rm xbosoncore/xboson-runtime:latest bash -c \
  'cat "/app/xboson.jar"' > xboson.jar
```

`gradle`

发布 `build/libs/*.jar` 到java类路径中, 启动(或重启) xBoson 平台软件.

当你用 [install.sh](https://github.com/xboson-core/xboson-runtime/blob/main/docs/getting-started/adv_install.md) 安装时可以:
```bash
docker cp your_module_file.jar xboson-rt:/app/lib/
docker restart xboson-rt
```


## 服务注册

注意, 任何一个软件包的扩展在加载时抛出异常会导致所有扩展包无法加载, 请注意输出日志 `user-js-module-loader` 相关文本.

该文件:
`src/main/resources/META-INF/services/com.xboson.exports.IJavaScriptModuleFactory` 
注册了当前 java 项目中导出的所有 js 模块, 每个模块一行.


## 工厂

`src/main/java/org/xboson/example/Factory.java`
该文件通过 `moduleName()` 导出模块名称, 脚本中 `require()` 相同包名即可引入实例, 注意包名是完整匹配, 你不能导出 `example` 然后导入 `example/hello`.

`src/main/java/org/xboson/example/Hello.java`
该对象最终导出到 javascript 环境中, 由使用该模块的用户调用.


## build.gradle

该脚本的正确运行依赖 `xBoson.war` 路径, 由于 war 中 class 文件在 `WEB-INF` 中, 所以会有一个提取过程.