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
 * @author mdr5325
 */
public class BankAccount {
    private double cash;
    
    public BankAccount(double _cash) 
    {
        this.cash = _cash;
    }
    
    // these methods seem to be thread safe
    public synchronized void addCash(double cashAmount)
    {
        this.cash += cashAmount;
        
        this.log("BANK ACCOUNT - Adding " + cashAmount + " To Bank Account");
        this.log("BANK ACCOUNT - Current Bank Account Total Is $" + cash);
    }
    
    public synchronized boolean removeCash(double cashAmount)
    {
        if (this.cash - cashAmount > 0)
        {
            this.cash -= cashAmount;
            
            this.log("BANK ACCOUNT - Removing " + cashAmount + " From Bank Account");
            this.log("BANK ACCOUNT - Current Bank Account Total Is $" + cash); 
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public synchronized double getCash()
    {
        return this.cash;
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
        System.out.println(_log);
    }
    
}
