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
public class InventoryAdjustment extends Transaction{
    Inventory inventory = Inventory.getInstance();
    private String itemName;
    private int quantity;
    
    public InventoryAdjustment(int id, String _itemName, int _quantity) {
        super(id);
        this.itemName = _itemName;
        this.quantity = _quantity;
    }
    
    @Override
    public void process()
    {
        Map<String, InventoryItem> items = inventory.getItems();
        
        // If we do not carry the item, cancel the transaction
        InventoryItem item = items.get(itemName);
        if (item != null) {
            
            if (item.getQuantity() + quantity >= 0)
            {
                item.increaseQuantityBy(this.quantity);
            }
            else
            {
                System.out.println("WE CANNOT ADJUST THIS QUANTITY OF INVENTORY");
            }
            
        } else {
            // item does not exist in the inventory
            System.out.println("WE DO NOT CARRY THIS ITEM");
        }
        
        
    }
    
}