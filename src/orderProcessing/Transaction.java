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

    public abstract void process();        
}

