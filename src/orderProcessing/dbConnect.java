/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

/**
 *
 * @author Matt
 */
import java.sql.*;

public class dbConnect {

public static void connection(){
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}

public static Connection ConnectionToMySql(){
    connection();
    
//    String host = "jdbc:mysql://localhost:3306/orderprocessing?user=mdr5325"; //FOR MAC 
    String host = "jdbc:mysql://localhost/orderprocessing"; //FOR WINDOWS PC
    String Username = "root";
    //String Password = "hKIQ_<tsT0lR";
    String Password = "";
    
    try {
//        Connection connect = DriverManager.getConnection(host);
        Connection connect = DriverManager.getConnection(host, Username, Password);
        System.out.println("Connection Successful");
        return connect;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }
}
