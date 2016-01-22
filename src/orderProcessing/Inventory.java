/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderProcessing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @author Matt
 */
public class Inventory {

    private Map<String, InventoryItem> items = new HashMap<String, InventoryItem>(); 
    
    public Inventory(){
         
    }
    
    public Inventory(HashMap<String, InventoryItem> _items)
    {
        items = _items;
    }
    
    public Map<String, InventoryItem> getItems()
    {
        return items;
    }
    
    public void addItem(InventoryItem _item)
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
    
    
    public void populateInventoryWithItems()
    {
        InventoryItem item1 = new InventoryItem("pen",2, .50);
        InventoryItem item2 = new InventoryItem("paper",1, 1.0);
        
        this.addItem(item1);
        this.addItem(item2);
        
        
    }
    
}
