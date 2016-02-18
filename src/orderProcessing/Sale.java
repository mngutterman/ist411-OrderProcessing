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
        Map<String, InventoryItem> items = inventory.getItems();
        InventoryItem item = items.get(itemName);
                    
        customer.addItemToCart(item, this.quantity);
        double cashToAddToInventory = item.getCost() * this.quantity;

        if (customer.processPayment(cashToAddToInventory))
        {
            item.decreaseQuantityBy(this.quantity);
            inventory.addMoneyToBankAccount(cashToAddToInventory);
            //inventory.addCash(cashToAddToInventory);
            System.out.println("Sale complete.   item: " +item.getName()+ " quantity: " + this.quantity);

        }
        else 
        {
            System.out.println("You do not have enough money. Transaction canceled.");
        }
        
        // Clearly not currently doing much with the users cart.
        customer.clearCart();
    }
    
    @Override
    public void run() {
        this.process();
    }
}
