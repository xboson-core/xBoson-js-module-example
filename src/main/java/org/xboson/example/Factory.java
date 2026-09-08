package org.xboson.example;

import com.xboson.exports.IJavaScriptModuleFactory;
import com.xboson.exports.JavaScriptModule;


public class Factory implements IJavaScriptModuleFactory {

  public String moduleName() {
    return "example/hello";
  }

  public void onRequire(JavaScriptModule module) {
    module.log().info("factory on require");
    module.exports = new Hello();
  }
}