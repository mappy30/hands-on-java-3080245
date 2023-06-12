package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;
import javax.xml.crypto.Data;

public class Menu {
  private Scanner scanner;

  public static void main(String args[]){

    System.out.println("Welcome to Globe International Bank!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null ){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }


    menu.scanner.close();
  }

  private Customer authenticateUser(){
    System.out.println("Enter Username:");
    String username = scanner.next();

    System.out.println("Enter Password:");
    String password = scanner.next();

    Customer customer = null;
    try{
    customer = Authenticator.login(username, password);
    }
    catch(LoginException e){
      System.out.println("Error occurred: "+ e.getMessage());
    }

    return customer;
  }

  /**
   * @param customer
   * @param account
   */
  private void showMenu(Customer customer, Account account){
    int selection = 0;
    while( selection !=4 && customer.isAuthenticated() ){
    System.out.println("=============================");
    System.out.println("Please select one of the following options:");
    System.out.println("1: Deposit");
    System.out.println("2: Withdraw");
    System.out.println("3: Check balance");
    System.out.println("4: Exit");
    System.out.println("=============================");

    selection = scanner.nextInt();
    double amount = 0;
    
    switch(selection){
      case 1:
      System.out.println("How much would you like to deposit?");
      amount = scanner.nextDouble();
      account.deposit(amount);
      break;

      case 2:
      System.out.println("How much would you like to withdraw?");
      amount = scanner.nextDouble();
      account.withdraw(amount);
      break;

      case 3:
      System.out.println("Current Balance: " + account.getBalance());
      break;

      case 4:
      Authenticator.logout(customer);
      System.out.println("Thanks for banking at Globe International Bank!");
      break;

      default:
      System.out.println("Enter a valid option.");
      break;
    }
    }
  }
}
