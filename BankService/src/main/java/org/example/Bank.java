package org.example;

import java.util.*;

public class Bank {
  HashMap<String, Client> data = new HashMap<>();

  public void createClient(String name, String surname, String phone) throws Exception {
    if (data.containsKey(phone)) {
      throw new Exception("This client already exist");
    } else {
      Client newClient = new Client(name, surname, phone);
      data.put(phone, newClient);
    }
  }

  public void removeClient(String phone) throws Exception {
    if (data.containsKey(phone)) {
      data.remove(phone);
    } else {
      throw new Exception("This client doesn't exist");
    }
  }

  public void getInfo(String phone) throws Exception {
    if (data.containsKey(phone)) {
      Client client = data.get(phone);
      System.out.println("Всего счетов:" + client.listOfAccounts.size());
      HashMap<String, Long> sum = new HashMap<>();
      client.listOfAccounts.forEach((key, value) -> {
        long count = sum.getOrDefault(value.checkType(), 0L) + value.checkBalance();
        sum.put(value.checkType(), count);
        System.out.println("Account: " + value.name + " ; Balance: " + value.checkBalance() + " Currency: " + value.checkType());
      });

      System.out.println("Summary balance:");
      sum.forEach((key, value) -> System.out.println(key + ": " + value));
    } else {
      throw new Exception("This client doesn't exist");
    }
  }
}
