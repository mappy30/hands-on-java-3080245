package bank;

import javax.xml.crypto.Data;

import bank.Exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException{
   if (amount < 1){
    throw new AmountException("Amount can not be less than 1 Rupees.");
   }
   else {
    double newBalance = balance + amount;
    this.setBalance(newBalance);
    DataSource.updateBalance(id, newBalance);
   }
  }
  public void withdraw(double amount) throws AmountException{
    if (amount < 0){
      throw new AmountException("Enter amount greater than 0")
    }
    else if(amount > getBalance()){
      throw new AmountException("You do not have sufficient funds");
    }
    else{
      double newbalance = balance - amount;
      setBalance(newbalance);
      DataSource.updateBalance(id, newbalance);
    }
}
