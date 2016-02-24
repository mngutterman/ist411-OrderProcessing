/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

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
        
        System.out.println("BANK ACCOUNT - Adding " + cashAmount + " To Bank Account");
        System.out.println("BANK ACCOUNT - Current Bank Account Total Is $" + cash);
    }
    
    public synchronized boolean removeCash(double cashAmount)
    {
        if (this.cash - cashAmount > 0)
        {
            this.cash -= cashAmount;
            
            System.out.println("BANK ACCOUNT - Removing " + cashAmount + " From Bank Account");
            System.out.println("BANK ACCOUNT - Current Bank Account Total Is $" + cash);
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
    
}
