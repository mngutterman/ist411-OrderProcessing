/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.Map;

/**
 *
 * @author mdr5325
 */
public class Sale extends Transaction{
    Inventory inventory = Inventory.getInstance();
    private String itemName;
    private int quantity;
    private Customer customer;
    
    public Sale(int id, String _itemName, int _quantity, Customer _customer) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
        this.customer = _customer;
    }
    
    @Override
    public synchronized void process()
    {
        System.out.println("TRANSACTION " + this.getTransactionID() + " - SALE - Start Transaction");

        Map<String, InventoryItem> items = inventory.getItems();
        InventoryItem item = items.get(itemName);
                    
        customer.addItemToCart(item, this.quantity);
        double cashToAddToInventory = item.getCost() * this.quantity;

        //synchronized(inventory.getBankAccount()){

        if (customer.processPayment(cashToAddToInventory))
        {
            item.decreaseQuantityBy(this.quantity);
            inventory.addMoneyToBankAccount(cashToAddToInventory);
            //inventory.addCash(cashToAddToInventory);
            
            System.out.println("TRANSACTION " + this.getTransactionID() + " - SALE - Decreased " + item.getName() + " Quantity By " + this.quantity);
            System.out.println("TRANSACTION " + this.getTransactionID() + " - SALE - Added " + cashToAddToInventory + " to Bank Account");             
        }
        else 
        {
            System.out.println("TRANSACTION " + this.getTransactionID() + " - SALE - Not Enough Money In Bank Account. Return Canceled");
        }
        //}
        
        // Clearly not currently doing much with the users cart.
        customer.clearCart();
        
        System.out.println("TRANSACTION " + this.getTransactionID() + " - SALE - End Transaction");
    }
    
    @Override
    public void run() {
        this.process();
    }
}
