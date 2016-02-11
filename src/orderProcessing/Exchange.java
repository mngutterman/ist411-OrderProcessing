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
public class Exchange extends Transaction{
    Inventory inventory = Inventory.getInstance();
    private String customerItemName;   // What they have
    private String inventoryItemName;  // What they want
    
    public Exchange(int id, String _customerItemName, String _inventoryItemName) {
        super(id);
        this.customerItemName = _customerItemName;
        this.inventoryItemName = _inventoryItemName;
    }
    
    @Override
    public void process()
    {
        
        Map<String, InventoryItem> items = inventory.getItems();
        
        // If we do not carry the item, cancel the transaction
        InventoryItem inventoryItem = items.get(inventoryItemName);
        if (inventoryItem != null) {
            InventoryItem customerItem = items.get(customerItemName);
            if (customerItem != null)
            {
                synchronized(inventory){
                    if (inventoryItem.getQuantity() > 0)
                    {
                        inventoryItem.decreaseQuantityBy(1);
                        customerItem.increaseQuantityBy(1);                    
                    }
                    else
                    {
                        System.out.println("We do not have this item in stock, sorry.");
                    }
                }
            }
            else
            {
                System.out.println("We do not carry the item you are trying to exchange, sorry");
            }
        } else {
            // item does not exist in the inventory
            System.out.println("We do not carry the item you want, sorry");
        }
        
    }
    
}
