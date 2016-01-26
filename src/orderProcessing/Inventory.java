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

    private/*public*/ Inventory(){
         populateInventoryWithItems();
    }
    
    // factory method
    public static Inventory getInstance( ) {
      return inventory;
    }
    
    protected/*public*/ Map<String, InventoryItem> getItems()
    {
        return items;
    }
    
    protected/*public*/ void addItem(InventoryItem _item)
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
    
    
    private void populateInventoryWithItems()
    {
        InventoryItem item1 = new InventoryItem("pen",2, .50);
        InventoryItem item2 = new InventoryItem("paper",1, 1.0);
        
        this.items.put(item1.getName(), item1);
        this.items.put(item2.getName(), item2);

        
    }
    
}
