package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {
  String db_file = "jdbc:sqlite:resources/bank.db";
  Connection connection = null;

  

  try{
    connection = DriverManager.getConnection(db_file);
    System.out.println( "Connected");
  }
  catch(SQLException e) {
    e.printStackTrace();
  }

  return connection;

}

public static Customer getCustomer(String username){
  String sql = "Select * from Customers where username = ?";
  Customer customer = null;

  try(Connection connection = connect();
    PreparedStatement statement = connection.prepareStatement(sql)) {
    statement.setString(1, username);
    try(ResultSet rs = statement.executeQuery()){
      customer = new Customer( 
      rs.getInt("Id"),
      rs.getString("name"),
      rs.getString("username"),
      rs.getString("password"),
      rs.getInt("account_id")
      );
    }
  }
  catch(SQLException e) {
    e.printStackTrace();
  }

  return customer;
}

public static Account getAccount(int account_id){
  String sql = "Select * from Accounts where id = ?";
  Account account = null;

  try(Connection connect = connect();
  PreparedStatement statement = connect.prepareStatement(sql)){
  statement.setInt(1, account_id);
   try(ResultSet rs = statement.executeQuery()){
      account = new Account(rs.getInt("id"), rs.getString("type"), rs.getDouble("balance"));
   }

  }
  catch (SQLException e) {
    // TODO: handle exception
    e.printStackTrace();
  }
return account;
}

public static void main(String args[]){

Customer customer = getCustomer("oleevesmc@naver.com");
System.out.println(customer.getName());
System.out.println(customer.getId());

Account account = getAccount(14645);
System.out.println(account.getBalance());
}
}
