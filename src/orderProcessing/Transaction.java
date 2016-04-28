/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Matt
 */
public abstract class Transaction implements Runnable {
    
    int transactionID;
    
    public Transaction(int id)
    {
        transactionID = id;
    }
    
    public int getTransactionID()
    {
        return transactionID;
    }
    
    public void log(String _log)
    {
        try{
            Connection conn = dbConnect.ConnectionToMySql();
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO `transactionlog`(`log`) VALUES ('" + _log + "')";
            stmt.executeUpdate(sql);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public abstract void process();        
}

