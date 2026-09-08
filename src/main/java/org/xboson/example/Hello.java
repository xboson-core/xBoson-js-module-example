package org.xboson.example;


public class Hello {

  /**
   * This variable is not thread-safe.
   */
  private int a = 1;

  public String world() {
    return "hello world "+ (++a);
  }
}