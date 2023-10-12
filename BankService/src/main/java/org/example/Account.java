package org.example;

import java.util.*;

public class Account {
  public String name;
  private long value = 0;
  private String type;

  public Account(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public long checkBalance() {
    return this.value;
  }

  public String checkType() {
    return this.type;
  }

  public void addBalance(long summa, String type) throws Exception {
    if (this.type == type) {
      value += summa;
    } else {
      throw new Exception("Incorrect type");
    }
  }

  public long removeBalance(long summa, String type) throws Exception {
    if (this.type == type) {
      if (value >= summa) {
        value -= summa;
        return summa;
      } else {
        throw new Exception("Sufficient funds");
      }
    } else {
      throw new Exception("Incorrect type");
    }
  }
}
