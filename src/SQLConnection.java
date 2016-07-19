//package oracle.jdbc.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class SQLConnection {
    void  getConnection() throws SQLException {

//    try{
//        Class.forName("com.mysql.jdbc.Driver");
    
        Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "axk159830");
    connectionProps.put("password", "axk159830");
    conn = DriverManager.getConnection 
        ("jdbc:oracle:thin:@onecore.myds.me:1521:xe","axk159830", "axk159830");

   if(conn !=null)
            System.out.println("Success"); 
//    System.out.println("Connected to database");
    
//    }catch (ClassNotFoundException e) {
    // TODO Auto-generated catch block
//    e.printStackTrace();
//   }
       
}
}
