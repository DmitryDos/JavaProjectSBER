package org.example;

import java.util.*;

public class Client {
  String name;
  String surname;
  String phone;

  HashMap<String, Account> listOfAccounts = new HashMap<>();

  Client(String name, String surname, String phone) {
    this.name = name;
    this.surname = surname;
    this.phone = phone;
  }

  public void createAccount(String accname, String type) {
    Account newAcc = new Account(accname, type);
    listOfAccounts.put(accname, newAcc);
  }

  public void createAccount(String accname, String type, long value) {
    try {
      Account newAcc = new Account(accname, type);
      newAcc.addBalance(value, type);
      listOfAccounts.put(accname, newAcc);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void addBalance(String accname, String type, long value) throws Exception {
    if (listOfAccounts.containsKey(accname)) {
      try {
        Account acc = listOfAccounts.get(accname);
        acc.addBalance(value, type);
      } catch (Exception e) {
        System.out.println(e);
      }
    } else {
      throw new Exception("This account doesn;t exist");
    }
  }

  public long removeBalance(String accname, String type, long value) throws Exception {
    if (listOfAccounts.containsKey(accname)) {
      try {
        Account acc = listOfAccounts.get(accname);
        long summ = acc.removeBalance(value, type);
        return summ;
      } catch (Exception e) {
        System.out.println(e);
      }

    } else {
      throw new Exception("This account doesn;t exist");
    }
    return 0;
  }

  public long checkBalance(String accname) throws Exception {
    if (listOfAccounts.containsKey(accname)) {
      try {
        Account acc = listOfAccounts.get(accname);
        long summ = acc.checkBalance();
        return summ;
      } catch (Exception e) {
        System.out.println(e);
      }

    } else {
      throw new Exception("This account doesn;t exist");
    }
    return 0;
  }
}
