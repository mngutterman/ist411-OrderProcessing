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
public class Inventory {

    //singleton instance of inventory
    private static Inventory inventory = new Inventory( );
    
    private Map<String, InventoryItem> items = new HashMap<String, InventoryItem>(); 
    //private double cash;
    private BankAccount bankAccount;
    
    private Inventory(){
         populateInventoryWithItems();
         this.bankAccount = new BankAccount(1000);
         //this.cash = 10000;
    }
    
    // factory method
    public synchronized static Inventory getInstance( ) {
      return inventory;
    }
    
    protected Map<String, InventoryItem> getItems()
    {
        return items;
    }
    
    protected synchronized void addItem(InventoryItem _item)
    {
        String itemName = _item.getName();
        int itemQuantity = _item.getQuantity();
        double itemCost = _item.getCost();
                
        InventoryItem item = items.get(itemName);
        
        if (item != null) {
            item.increaseQuantityBy(itemQuantity);
        }
        // item has not been created in our hashMap. create it and set its quantity / cost
        else 
        {
            item = new InventoryItem(itemName, itemQuantity, itemCost);
            items.put(itemName,item);
        }
    }
    
    protected synchronized BankAccount getBankAccount()
    {
        return this.bankAccount;
    }
    
    protected synchronized void addMoneyToBankAccount(double cash)
    {
        this.bankAccount.addCash(cash);
    }
    
    protected synchronized void removeMoneyFromBankAccount(double cash)
    {
        this.bankAccount.removeCash(cash);
    }
    
    protected synchronized double getCashFromBankAccount()
    {
        return this.bankAccount.getCash();
    }
    
    private void populateInventoryWithItems()
    {
        InventoryItem item1 = new InventoryItem("pen",2, .50);
        InventoryItem item2 = new InventoryItem("paper",1, 3.0);
        
        this.items.put(item1.getName(), item1);
        this.items.put(item2.getName(), item2);
    }
    
}
