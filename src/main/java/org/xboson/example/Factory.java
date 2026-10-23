package org.xboson.example;

import com.xboson.exports.IJavaScriptModuleFactory;
import com.xboson.exports.JavaScriptModule;


public class Factory implements IJavaScriptModuleFactory {

  public String moduleName() {
    return "example/hello";
  }

  public void onRequire(JavaScriptModule module) {
    /**
      * The log object should be stored as a property; 
      * the logger is thread-safe.
      */
    module.log().info("factory on require");
    module.exports = new Hello();
  }
}