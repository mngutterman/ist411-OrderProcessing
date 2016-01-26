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
public class Customer {
    private int customerID;
    private String customerName;
    
    public Customer(int _customerID, String _customerName){
        customerID = _customerID;
        customerName = _customerName;
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
    
    public void submitTransaction(Transaction transaction)
    {
        transaction.process();
    }
}
