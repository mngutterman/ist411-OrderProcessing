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
    protected synchronized void addCash(double cashAmount)
    {
        System.out.println("adding $" + cashAmount + " to inventory cash");
        this.cash += cashAmount;
        System.out.println("inventory cash total now:    $" + cash);
    }
    
    protected synchronized boolean removeCash(double cashAmount)
    {
        System.out.println("removing $" + cashAmount + " from inventory cash");
        if (this.cash - cashAmount > 0)
        {
            this.cash -= cashAmount;
            System.out.println("inventory cash total now:    $" + cash);
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    protected synchronized double getCash()
    {
        return this.cash;
    }
    
}
