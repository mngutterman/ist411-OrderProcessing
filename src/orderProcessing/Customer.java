/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Matt
 */
public class Customer implements Runnable{
    private int customerID;
    private String customerName;
    private double cash;
    
    // cart is a dictionary of item : quanity
    private Map<InventoryItem, Integer> cart = new HashMap<InventoryItem, Integer>(); 

    public Customer(int _customerID, String _customerName, double _cash){
        customerID = _customerID;
        customerName = _customerName;
        cash = _cash;
    }
    
    public int getCustomerID()
    {
        return customerID;
    }
    public void setCustomerID(int _customerID)
    {
        customerID = _customerID;
    }
    public String getCustomerName()
    {
        return customerName;
    }
    public void setCustomerName(String _customerName)
    {
        customerName = _customerName;
    }    
    public void addItemToCart(InventoryItem item, Integer quantity)
    {
        cart.put(item, quantity);
    }
    
    public void clearCart()
    {
        cart.clear();
    }
    
    public synchronized void submitTransaction(Transaction transaction)
    {
        transaction.process();
    }
    
    public boolean processPayment(double paymentAmount)
    {
        if (paymentAmount <= this.cash){
            this.cash -= paymentAmount;
            return true;
        } 
        else 
        {
            return false;
        }
    }

    @Override
    public void run() {
        Order order = new Order(0,"pen",2, this);
        this.submitTransaction(order);
        
        
        
        //Return ret = new Return(0, "pen", 50000);
        //customer1.submitTransaction(ret);
        
        //InventoryAdjustment adj = new InventoryAdjustment(0, "pen", 2);
        //customer1.submitTransaction(adj);
        
        //Exchange exchange = new Exchange(0, "paper", "pen");
        //customer1.submitTransaction(exchange);
    }
}
