package org.xboson.example;

import com.xboson.exports.JavaScriptModule;

public class Hello {

  /**
   * This variable is not thread-safe.
   */
  private int a = 1;

  public String world() {
    return "hello world "+ (++a);
  }

  public Object openSimpleResource() {
    AutoCloseable ac = new SimpleResource();
    /**
      * Once the API returns (including in the event of an exception), 
      * the resource is automatically released.
      */
    JavaScriptModule.autoClose(ac);
    return ac;
  }

  private class SimpleResource implements AutoCloseable {
    @Override
    public void close() {
      System.out.println("Resource closed!");
    }
  }
}