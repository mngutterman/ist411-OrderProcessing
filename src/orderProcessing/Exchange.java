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
    private String customerItemName;
    private String inventoryItemName;
    
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
                if (inventoryItem.getQuantity() > 0)
                {
                    inventoryItem.decreaseQuantityBy(1);
                    customerItem.increaseQuantityBy(1);                    
                }
                else
                {
                    System.out.println("WE DO NOT HAVE ENOUGH IN STOCK");
                }
            }
            else
            {
                System.out.println("WE DO NOT CARRY YOUR ITEM");
            }
        } else {
            // item does not exist in the inventory
            System.out.println("WE DO NOT CARRY THIS ITEM");
        }
        
        
    }
    
}
